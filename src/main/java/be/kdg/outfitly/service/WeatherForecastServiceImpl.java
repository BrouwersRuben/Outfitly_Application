package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.repository.WeatherForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);
    private final WeatherForecastRepository weatherForecastRepository;

    @Autowired
    public WeatherForecastServiceImpl(WeatherForecastRepository weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    //No scheduling needed, method checks the repository for weatherforecasts, if the one they get for that city is older than 45 minutes, it will get a new one from the api.
    public WeatherForecast findByCountryAndCity(String country, String city) {
        logger.debug("Find by country: " + country + " and city: " + city);
        List<WeatherForecast> forecasts = weatherForecastRepository.findAll().stream().filter(weatherData -> weatherData.getCountryCode().equals(country) && weatherData.getCity().equals(city)).collect(Collectors.toList());
        forecasts = forecasts.stream()
                .filter(forecast -> forecast.getDate().isAfter(LocalDateTime.now().minusMinutes(45L)))
                .sorted(Comparator.comparing(WeatherForecast::getDate))
                .collect(Collectors.toList());
        if(forecasts.size()==0){
            return create(country, city);
        } else{
            return forecasts.get(0);
        }
    }

    @Override
    public WeatherForecast create(String country, String city) {
        return weatherForecastRepository.save(WeatherForecast.currentForecastForCountryCity(country, city));
    }


}
