package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.DailyWeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherForecastRepository extends ListRepository<DailyWeatherForecast> {
    private final Logger logger = LoggerFactory.getLogger(DailyWeatherForecastRepository.class);

    public DailyWeatherForecastRepository(){
        logger.debug("Creating Daily Weather API Repository");
    }
}
