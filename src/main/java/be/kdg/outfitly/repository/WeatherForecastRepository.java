package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Integer> {
}
