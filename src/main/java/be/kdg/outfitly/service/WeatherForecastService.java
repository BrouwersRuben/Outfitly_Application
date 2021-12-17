package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.WeatherForecast;

import java.time.LocalDateTime;

public interface WeatherForecastService {
    WeatherForecast findByCountryAndCity(String country, String city);
    WeatherForecast create(String country, String city);
}
