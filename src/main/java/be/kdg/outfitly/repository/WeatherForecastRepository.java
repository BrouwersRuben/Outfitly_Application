package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;

import java.time.LocalDateTime;

public interface WeatherForecastRepository {
    WeatherForecast findByDate(LocalDateTime time);
    WeatherForecast findByCountryAndCity(String country, String city);
}
