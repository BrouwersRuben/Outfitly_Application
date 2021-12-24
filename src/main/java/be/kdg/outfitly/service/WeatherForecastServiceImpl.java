package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.repository.WeatherForecastRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);
    private final WeatherForecastRepository weatherForecastRepository;

    @Autowired
    public WeatherForecastServiceImpl(WeatherForecastRepository weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    //No scheduling needed, method checks the repository for weatherforecasts, if the one they get for that city is older than 45 minutes, it will get a new one from the api.
    public WeatherForecast getNewByCountryCodeAndCity(String countryCode, String city) {
        logger.debug("Find by country: " + countryCode + " and city: " + city);

        List<WeatherForecast> forecasts = weatherForecastRepository.findAll()
                .stream()
                .filter(weatherData -> weatherData.getCountryCode().equals(countryCode) && weatherData.getCity().equals(city))
                .collect(Collectors.toList());

        List<WeatherForecast> onlyNewForecasts = forecasts.stream()
                .filter(forecast -> forecast.getDate().isAfter(LocalDateTime.now().minusMinutes(45L)))
                .sorted(Comparator.comparing(WeatherForecast::getDate))
                .collect(Collectors.toList());

        if (onlyNewForecasts.size() == 0) {
            //delete old, unused data
            forecasts.forEach(forecast -> weatherForecastRepository.deleteById(forecast.getId()));
            //create new one
            return create(countryCode, city);
        } else {
            return forecasts.get(0);
        }
    }

    @Override
    public WeatherForecast create(String countryCode, String city) {
        WeatherForecast weatherForecast = new WeatherForecast();
        JSONObject currentWeatherAPIData;
        JSONObject hourlyWeatherDataAPI;

        try {
            currentWeatherAPIData = retrieveAPIData("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + "&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c");

        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the first API.");
            logger.error(Arrays.toString(e.getStackTrace()));
            return null;
        }

        weatherForecast.setDate(LocalDateTime.now());
        weatherForecast.setCountryCode(countryCode);
        weatherForecast.setCity(city);
        weatherForecast.setCurrentTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp"))));
        weatherForecast.setCurrentFeelsLikeTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("feels_like"))));
        weatherForecast.setCurrentHumidity(Integer.parseInt(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("humidity"))));
        weatherForecast.setCurrentWindSpeed(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("wind").get("speed"))));
        weatherForecast.setHighestTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp_max"))));
        weatherForecast.setLowestTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp_min"))));
        weatherForecast.setWeatherDescription(String.valueOf(currentWeatherAPIData.getJSONArray("weather").getJSONObject(0).get("description")));
        weatherForecast.setLatitude(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("coord").get("lat"))));
        weatherForecast.setLongitude(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("coord").get("lon"))));


        try {
            hourlyWeatherDataAPI = retrieveAPIData("https://api.openweathermap.org/data/2.5/onecall?lat=" + weatherForecast.getLatitude() + "&lon=" + weatherForecast.getLongitude() + "&exclude=minutely&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c");

        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the second API.");
            return null;
        }

        weatherForecast.setRainProbability(Double.parseDouble(String.valueOf(hourlyWeatherDataAPI.getJSONArray("daily").getJSONObject(0).get("pop"))) * 100);
        weatherForecast.setWeatherIcon(String.valueOf("http://openweathermap.org/img/wn/" + hourlyWeatherDataAPI.getJSONObject("current").getJSONArray("weather").getJSONObject(0).get("icon")) + "@4x.png");

        //Is this easily possible because there is an iterator counter.
        //TODO: Change to stream
        for (int i = 0; i < hourlyWeatherDataAPI.getJSONArray("hourly").length(); i++) {
            double temperature = Double.parseDouble(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("temp")));
            long timestamp = Long.parseLong(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("dt")));
            weatherForecast.getDailyTemperatures().put(timestamp, temperature);
        }

        if (hourlyWeatherDataAPI.has("alerts")) {
            //TODO: Change to stream
            for (int i = 0; i < hourlyWeatherDataAPI.getJSONArray("alerts").length(); i++) {

                long timestamp = Long.parseLong(String.valueOf(hourlyWeatherDataAPI.getJSONArray("alerts").getJSONObject(i).get("start")));
                String description = String.valueOf(hourlyWeatherDataAPI.getJSONArray("alerts").getJSONObject(i).get("description"));

                weatherForecast.getWeatherAlerts().put(timestamp, description);
            }
        }


        return weatherForecastRepository.save(weatherForecast);
    }

    private JSONObject retrieveAPIData(String apiLink) throws Exception {
        URIBuilder builder = new URIBuilder(apiLink);
        HttpGet get = new HttpGet(builder.build());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JSONObject json;

        try (CloseableHttpResponse response = httpclient.execute(get)) {
            json = null;
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Bad response status code: {}.", response.getStatusLine().getStatusCode());
//                return;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String rawResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                json = new JSONObject(rawResult);
            }

            if (json == null) {
                logger.error("No weather forecast data retrieved!");
            } else {
                logger.debug("Forecast data successfully retrieved!");
            }
        }
        return json;
    }

    public boolean isValidCountryCodeAndCity(String countryCode, String city) {
        return isValidLocation(city + "," + countryCode);
    }

    public boolean isValidLocation(String location) {

        logger.debug("Validating location: "+location);

        if (location.length() <= 3) {
            logger.error("Invalid location. City not chosen. - " + location);
            return false;
        }

        JSONObject weatherAPIData;
        try {
            weatherAPIData = retrieveAPIData(location);
        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the API.");
            logger.error(Arrays.toString(e.getStackTrace()));
            return false;
        }
        boolean valid = !weatherAPIData.get("cod").equals("404");


        logger.debug("Location: " + location + " is " + (valid ? "" : "not") + " valid.");

        return valid;
    }

}
