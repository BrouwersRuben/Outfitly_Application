package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.repository.WeatherForecastRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);
    private final WeatherForecastRepositoryImpl weatherForecastRepository;

    @Autowired
    public WeatherForecastServiceImpl(WeatherForecastRepositoryImpl weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    public WeatherForecast findByDate(LocalDateTime time) {
        return weatherForecastRepository.findByDate(time);
    }

    @Override
    public WeatherForecast findByCountryAndCity(String country, String city){
        logger.debug("Find by country: "+country+" and city: "+city);
        return weatherForecastRepository.findByCountryAndCity(country, city);
    }

}
