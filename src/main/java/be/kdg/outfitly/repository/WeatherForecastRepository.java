package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherForecastRepository {
    List<WeatherForecast> read();
}
