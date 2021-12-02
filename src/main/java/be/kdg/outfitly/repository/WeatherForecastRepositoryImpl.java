package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherForecastRepositoryImpl extends ListRepository<WeatherForecast> implements WeatherForecastRepository{
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryImpl.class);

    public WeatherForecastRepositoryImpl(){
        logger.debug("Creating Weather API repository");
    }

}
