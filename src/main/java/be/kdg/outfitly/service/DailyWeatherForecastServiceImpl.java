package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.DailyWeatherForecast;
import be.kdg.outfitly.repository.DailyWeatherForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherForecastServiceImpl implements DailyWeatherForecastService {
    private final DailyWeatherForecastRepository dailyWeatherForecastRepository;
    private final Logger logger = LoggerFactory.getLogger(DailyWeatherForecastServiceImpl.class);

    @Autowired
    public DailyWeatherForecastServiceImpl(DailyWeatherForecastRepository dailyWeatherForecastRepository) {
        this.dailyWeatherForecastRepository = dailyWeatherForecastRepository;
    }

    @Override
    public DailyWeatherForecast findByCountryAndCity(String country, String city){
        logger.debug("Find by country: "+country+" and city: "+city);
        return dailyWeatherForecastRepository.read().stream().filter(weatherData -> weatherData.getCountryCode().equals(country) && weatherData.getCity().equals(city)).findFirst().get();
    }
}

