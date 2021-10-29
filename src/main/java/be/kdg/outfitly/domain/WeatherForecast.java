package be.kdg.outfitly.domain;

import java.time.LocalDateTime;

// TODO: This is only for the CURRENT weather forecast, we still need a separate class for future forecast I guess.
public class WeatherForecast extends Entity {

    private LocalDateTime date;
    private String city;
    private String country;
    private double temperature;
    private double feelsLikeTemperature;
    private double lowestTemperature;
    private double highestTemperature;
    private double windSpeed;
    private int humidity;
    private String weatherDescription;

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

    public WeatherForecast() {
    }

    public static WeatherForecast randomForecast(){
        WeatherForecast wf = new WeatherForecast();
        wf.lowestTemperature = 16;
        wf.weatherDescription = "";
        return wf;
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
