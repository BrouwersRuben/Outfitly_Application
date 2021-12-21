package be.kdg.outfitly.domain;

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

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: This is only for the CURRENT weather forecast, we still need a separate class for future forecast I guess.
@javax.persistence.Entity
@Table(name = "weather_forecasts")
public class WeatherForecast extends Entity {

    private static final Logger logger = LoggerFactory.getLogger(WeatherForecast.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "forecast_timestamp", nullable = false)
    private LocalDateTime date;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "current_temperature", nullable = false)
    private double currentTemperature;

    @Column(name = "current_feels_like_temperature", nullable = false)
    private double currentFeelsLikeTemperature;

    @Column(name = "current_humidity", nullable = false)
    private int currentHumidity;

    @Column(name = "wind_speed", nullable = false)
    private double currentWindSpeed;

    @Column(name = "lowest_temperature", nullable = false)
    private double lowestTemperature;

    @Column(name = "highest_temperature", nullable = false)
    private double highestTemperature;

    @Column(name = "rain_probability", nullable = false)
    private double rainProbability;

    @Column(name = "weather_description", nullable = false)
    private String weatherDescription;

    @Column(name = "weather_icon", nullable = false)
    private String weatherIcon;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longtitude", nullable = false)
    private double longitude;


    @ElementCollection
    private Map<Long, Double> dailyTemperatures = new HashMap<>();

    @ElementCollection
    private Map<Long, String> weatherAlerts = new HashMap<>();


    public WeatherForecast(int id, LocalDateTime date, String city, String countryCode, double currentTemperature, double currentFeelsLikeTemperature, int currentHumidity, double currentWindSpeed, double lowestTemperature, double highestTemperature, double rainProbability, String weatherDescription, String weatherIcon, double latitude, double longitude) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.countryCode = countryCode;
        this.currentTemperature = currentTemperature;
        this.currentFeelsLikeTemperature = currentFeelsLikeTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
        this.lowestTemperature = lowestTemperature;
        this.highestTemperature = highestTemperature;
        this.rainProbability = rainProbability;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dailyTemperatures =  dailyTemperatures;
        this.weatherAlerts =  weatherAlerts;
    }

//    public WeatherForecast(LocalDateTime date, double currentTemperature, int currentHumidity, String email) {
//        this.date = date;
//        this.currentTemperature = currentTemperature;
//        this.currentHumidity = currentHumidity;
//        this.email = email;
//    }

    public WeatherForecast() {

    }

//    public static WeatherForecast currentForecastForCountryCity(String countryCode, String city) {
//        WeatherForecast weatherForecast = new WeatherForecast();
//        JSONObject currentWeatherAPIData;
//        JSONObject hourlyWeatherDataAPI;
//
//        try {
//            currentWeatherAPIData = retrieveAPIData("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + "&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c");
//
//        } catch (Exception e) {
//            logger.error("An error occurred while data was being retrieved from the first API.");
//            return null;
//        }
//
//        weatherForecast.setDate(LocalDateTime.now());
//        weatherForecast.setCountryCode(countryCode);
//        weatherForecast.setCity(city);
//        weatherForecast.setCurrentTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp"))));
//        weatherForecast.setCurrentFeelsLikeTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("feels_like"))));
//        weatherForecast.setCurrentHumidity(Integer.parseInt(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("humidity"))));
//        weatherForecast.setCurrentWindSpeed(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("wind").get("speed"))));
//        weatherForecast.setHighestTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp_max"))));
//        weatherForecast.setLowestTemperature(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("main").get("temp_min"))));
//        weatherForecast.setWeatherDescription(String.valueOf(currentWeatherAPIData.getJSONArray("weather").getJSONObject(0).get("description")));
//        weatherForecast.setLatitude(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("coord").get("lat"))));
//        weatherForecast.setLongitude(Double.parseDouble(String.valueOf(currentWeatherAPIData.getJSONObject("coord").get("lon"))));
//
//
//        try {
//            hourlyWeatherDataAPI = retrieveAPIData("https://api.openweathermap.org/data/2.5/onecall?lat="+weatherForecast.getLatitude()+"&lon="+weatherForecast.getLongitude()+"&exclude=minutely&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c");
//
//        } catch (Exception e) {
//            logger.error("An error occurred while data was being retrieved from the second API.");
//            return null;
//        }
//
//        weatherForecast.setRainProbability(Double.parseDouble(String.valueOf(hourlyWeatherDataAPI.getJSONArray("daily").getJSONObject(0).get("pop"))) * 100);
//        weatherForecast.setWeatherIcon(String.valueOf("http://openweathermap.org/img/wn/" + hourlyWeatherDataAPI.getJSONObject("current").getJSONArray("weather").getJSONObject(0).get("icon")) + "@4x.png");
//
//        for (int i = 0; i < hourlyWeatherDataAPI.getJSONArray("hourly").length(); i++) {
////            weatherForecast.dailyTemperatures.add(Double.parseDouble(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("temp"))));
////            weatherForecast.dailyTemperatureTimestamps.add(Long.parseLong(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("dt"))));
//            double temperature = Double.parseDouble(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("temp")));
//            long timestamp = Long.parseLong(String.valueOf(hourlyWeatherDataAPI.getJSONArray("hourly").getJSONObject(i).get("dt")));
//            weatherForecast.getDailyTemperatures().put(timestamp, temperature);
//        }
//
//        if (hourlyWeatherDataAPI.has("alerts")) {
//            for (int i = 0; i <hourlyWeatherDataAPI.getJSONArray("alerts").length(); i++) {
//
//                long timestamp = Long.parseLong(String.valueOf(hourlyWeatherDataAPI.getJSONArray("alerts").getJSONObject(i).get("start")));
//                String description = String.valueOf(hourlyWeatherDataAPI.getJSONArray("alerts").getJSONObject(i).get("description"));
//
//                weatherForecast.getWeatherAlerts().put(timestamp, description);
//            }
//        }
//
//        logger.debug("Weather forecast object created: ");
//
//        return weatherForecast;
//    }

//    private static JSONObject retrieveAPIData(String apiLink) throws Exception {
//        // TODO: Reload api on refresh
//        URIBuilder builder = new URIBuilder(apiLink);
//        HttpGet get = new HttpGet(builder.build());
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        JSONObject json;
//
//        try (CloseableHttpResponse response = httpclient.execute(get)) {
//            json = null;
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                logger.error("Bad response status code: {}.", response.getStatusLine().getStatusCode());
////                return;
//            }
//
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                String rawResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//                json = new JSONObject(rawResult);
//            }
//
//            if (json == null) {
//                logger.error("No weather forecast data retrieved!");
//            } else {
//                logger.debug("Forecast data successfully retrieved!");
//            }
//        }
//        return json;
//    }





    // Getters
    @Override
    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getCurrentFeelsLikeTemperature() {
        return currentFeelsLikeTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public double getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public double getLowestTemperature() {
        return lowestTemperature;
    }

    public double getHighestTemperature() {
        return highestTemperature;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Map<Long, Double> getDailyTemperatures() {
        return dailyTemperatures;
    }

    public Map<Long, String> getWeatherAlerts() {
        return weatherAlerts;
    }


    // Setters
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setCurrentFeelsLikeTemperature(double currentFeelsLikeTemperature) {
        this.currentFeelsLikeTemperature = currentFeelsLikeTemperature;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public void setCurrentWindSpeed(double currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }

    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature = highestTemperature;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    // https://openweathermap.org/weather-conditions
    public boolean isGoingToRain() {
        return getWeatherDescription().toLowerCase().contains("rain") || getWeatherDescription().toLowerCase().contains("drizzle");
    }

    public void setDailyTemperatures(HashMap<Long, Double> dailyTemperatures) {
        this.dailyTemperatures = dailyTemperatures;
    }

    public void setWeatherAlerts(HashMap<Long, String> weatherAlerts) {
        this.weatherAlerts = weatherAlerts;
    }
}
