package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.WeatherForecast;

import java.time.LocalDateTime;

public interface WeatherForecastService {

    WeatherForecast findByDate(LocalDateTime time);
}
