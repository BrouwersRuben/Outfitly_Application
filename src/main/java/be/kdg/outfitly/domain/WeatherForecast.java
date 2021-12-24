package be.kdg.outfitly.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    }

    public WeatherForecast() {

    }

    // Getters & Setters

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCurrentFeelsLikeTemperature() {
        return currentFeelsLikeTemperature;
    }

    public void setCurrentFeelsLikeTemperature(double currentFeelsLikeTemperature) {
        this.currentFeelsLikeTemperature = currentFeelsLikeTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(double currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public double getLowestTemperature() {
        return lowestTemperature;
    }

    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }

    public double getHighestTemperature() {
        return highestTemperature;
    }

    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature = highestTemperature;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Map<Long, Double> getDailyTemperatures() {
        return dailyTemperatures;
    }

    public void setDailyTemperatures(HashMap<Long, Double> dailyTemperatures) {
        this.dailyTemperatures = dailyTemperatures;
    }

    public Map<Long, String> getWeatherAlerts() {
        return weatherAlerts;
    }

    public void setWeatherAlerts(HashMap<Long, String> weatherAlerts) {
        this.weatherAlerts = weatherAlerts;
    }

    // https://openweathermap.org/weather-conditions
    public boolean isGoingToRain() {
        return getWeatherDescription().toLowerCase().contains("rain") || getWeatherDescription().toLowerCase().contains("drizzle");
    }
}
