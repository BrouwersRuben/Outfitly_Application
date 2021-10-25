package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WeatherForecastRepository extends ListRepository<WeatherForecast>{
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastRepository.class);

    public WeatherForecastRepository(){
        logger.debug("Creating Weather API repository");
    }
}
