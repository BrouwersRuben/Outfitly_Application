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

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

// TODO: This is only for the CURRENT weather forecast, we still need a separate class for future forecast I guess.
public class WeatherForecast extends Entity {

    private LocalDateTime date;
    private String city;
    private String countryCode;
    private double temperature;
    private double feelsLikeTemperature;
    private double lowestTemperature;
    private double highestTemperature;
    private double windSpeed;
    private int humidity;
    private String weatherDescription;

    private static final Logger logger = LoggerFactory.getLogger(WeatherForecast.class);

    public WeatherForecast(LocalDateTime date, String city, String countryCode, double temperature, double feelsLikeTemperature, double lowestTemperature, double highestTemperature, double windSpeed, int humidity, String weatherDescription) {
        this.date = date;
        this.city = city;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.lowestTemperature = lowestTemperature;
        this.highestTemperature = highestTemperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
    }

    public WeatherForecast() {
    }

    public static WeatherForecast currentForecastForCountryCity(String countryCode, String city) {
        WeatherForecast wf = new WeatherForecast();
        JSONObject weatherAPIData;
        try {
            weatherAPIData = retrieveAPIData(city + "," + countryCode);
        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the API.");
            return null;
        }

        wf.setDate(LocalDateTime.now());
        wf.setCountryCode(countryCode);
        wf.setCity(city);
        wf.setTemperature(Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp"))));
        wf.setFeelsLikeTemperature(Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("feels_like"))));
        wf.setLowestTemperature(Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp_min"))));
        wf.setHighestTemperature(Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp_max"))));
        wf.setWindSpeed(Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("wind").get("speed"))));
        wf.setHumidity(Integer.parseInt(String.valueOf(weatherAPIData.getJSONObject("main").get("humidity"))));
        wf.setWeatherDescription(String.valueOf(weatherAPIData.getJSONArray("weather").getJSONObject(0).get("main")));

        logger.debug("Weather forecast object created: ");

        return wf;
    }

    private static JSONObject retrieveAPIData(String location) throws Exception {
        String APILink = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c";
        URIBuilder builder = new URIBuilder(APILink);
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


    public static boolean isValidCountryCity(String countryCode, String city) {
        return isValidLocation(city+","+countryCode);
    }

    public static boolean isValidLocation(String location){

        if(location.length() <= 3){
            logger.debug("Invalid location. City not chosen. - " + location);
            return false;
        }

        JSONObject weatherAPIData;
        try {
            weatherAPIData = retrieveAPIData(location);
        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the API.");
            return false;
        }
        boolean valid = !weatherAPIData.get("cod").equals("404");


        logger.debug("Location: " + location + " is "+ (valid? "" :"not")+ " valid.");

        return valid;
    }


    // Note: I can retrieve any info from the API, though retrieving some data might be tricky due to the fact that we're using a trial version.
    // https://openweathermap.org/api
    // Some data can be only retrieved with coordinates, feasible.


    public LocalDateTime getTimeOfReading() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public double getLowestTemperature() {
        return lowestTemperature;
    }

    public double getHighestTemperature() {
        return highestTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public LocalDateTime getDate() {
        return date;
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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }

    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature = highestTemperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    // https://openweathermap.org/weather-conditions
    public boolean isGoingToRain() {
        //TODO
        return getWeatherDescription().equals("rain") || getWeatherDescription().equals("drizzle");
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "date=" + date +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", lowestTemperature=" + lowestTemperature +
                ", highestTemperature=" + highestTemperature +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }
}
