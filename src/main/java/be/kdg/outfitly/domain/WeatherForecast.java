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

// TODO: This is only for the CURRENT weather forecast, we still need a separate class for future forecast I guess.
@javax.persistence.Entity
@Table(name = "current_weather_forecast")
public class WeatherForecast extends Entity {

    private static final Logger logger = LoggerFactory.getLogger(WeatherForecast.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "forecast_timestamp", nullable = false)
    private LocalDateTime date;

    //TODO: city + country --> location
    @Column(name = "forecast_location", nullable = false)
    private String city;

    private String country;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "humidity", nullable = false)
    private int humidity;

    @Column(name = "feels_like_temperature", nullable = false)
    private double feelsLikeTemperature;

    @Column(name = "lowest_temperature", nullable = false)
    private double lowestTemperature;

    @Column(name = "highest_temperature", nullable = false)
    private double highestTemperature;

    @Column(name = "wind_speed", nullable = false)
    private double windSpeed;

    @Column(name = "weather_description", nullable = false)
    private String weatherDescription;

    protected WeatherForecast() {
    }

    public WeatherForecast(LocalDateTime date, String city, String country, double temperature, double feelsLikeTemperature, double lowestTemperature, double highestTemperature, double windSpeed, int humidity, String weatherDescription) {
        this.date = date;
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.lowestTemperature = lowestTemperature;
        this.highestTemperature = highestTemperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
    }

    public static WeatherForecast currentForecastForCountryCity(String country, String city){
        WeatherForecast wf = new WeatherForecast();
        JSONObject weatherAPIData;
        try {
            weatherAPIData = retrieveAPIData(city + "," + country);
        }catch (Exception e){
            logger.error("An error occurred while data was being retrieved from the API.");
            return null;
        }

        wf.setDate(LocalDateTime.now());
        wf.setCountry(country);
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

    private static JSONObject retrieveAPIData(String location) throws Exception{
        String APILink = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c";
        // TODO: Reload api on refresh
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

            if (json ==null) {
                logger.error("No weather forecast data retrieved!");
            } else {
                logger.debug("Forecast data successfully retrieved!");
            }
        }
        return json;
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

    public String getCountry() {
        return country;
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

    public void setCountry(String country) {
        this.country = country;
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
                ", country='" + country + '\'' +
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
