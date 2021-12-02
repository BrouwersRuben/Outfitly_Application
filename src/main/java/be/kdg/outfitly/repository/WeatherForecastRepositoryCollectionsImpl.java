package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JavaCollections")
public class WeatherForecastRepositoryCollectionsImpl extends ListRepository<WeatherForecast> implements WeatherForecastRepository{
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryCollectionsImpl.class);

    public WeatherForecastRepositoryCollectionsImpl(){
        logger.debug("Creating Weather forecast repository (Collections)");
    }

}
