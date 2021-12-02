package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class WeatherForecastRepositoryImpl extends ListRepository<WeatherForecast> implements WeatherForecastRepository{
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryImpl.class);

    public WeatherForecastRepositoryImpl(){
        logger.debug("Creating Weather API repository");
    }

    @Override
    public WeatherForecast findByDate(LocalDateTime time) {
        return weatherForecastRepository.read().stream().filter(weatherData -> weatherData.getTimeOfReading().equals(time)).findFirst().get();
    }

    @Override
    public WeatherForecast findByCountryAndCity(String country, String city){
        logger.debug("Find by country: "+country+" and city: "+city);
        return weatherForecastRepository.read().stream().filter(weatherData -> weatherData.getCountry().equals(country) && weatherData.getCity().equals(city)).findFirst().get();
    }
}
