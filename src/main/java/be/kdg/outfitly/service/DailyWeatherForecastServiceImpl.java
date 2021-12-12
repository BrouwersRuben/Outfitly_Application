/*
package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.DailyWeatherForecast;
import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.repository.DailyWeatherForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DailyWeatherForecastServiceImpl implements DailyWeatherForecastService {
    private final DailyWeatherForecastRepository dailyWeatherForecastRepository;
    private final WeatherForecastService weatherForecastService;
    private final Logger logger = LoggerFactory.getLogger(DailyWeatherForecastServiceImpl.class);


    @Autowired
    public DailyWeatherForecastServiceImpl(DailyWeatherForecastRepository dailyWeatherForecastRepository, WeatherForecastService weatherForecastService) {
        this.dailyWeatherForecastRepository = dailyWeatherForecastRepository;
        this.weatherForecastService = weatherForecastService;
    }



    @Override
    public DailyWeatherForecast findByCountryAndCity(String country, String city){
        logger.debug("Find by country: "+country+" and city: "+city);

        List<DailyWeatherForecast> forecasts = dailyWeatherForecastRepository.findAll().stream().filter(weatherData -> weatherData.getCountryCode().equals(country) && weatherData.getCity().equals(city)).collect(Collectors.toList());
        forecasts = forecasts.stream()
                .filter(forecast -> forecast.getDate().isAfter(LocalDateTime.now().minusMinutes(45L)))
                .sorted(Comparator.comparing(DailyWeatherForecast::getDate))
                .collect(Collectors.toList());
        if(forecasts.size()==0){
            return create(country, city);
        } else{
            return forecasts.get(0);
        }

    }

    public DailyWeatherForecast create(String country, String city) {

        WeatherForecast wf = weatherForecastService.findByCountryAndCity(country, city);
        //TODO: change this
        return dailyWeatherForecastRepository.save(DailyWeatherForecast.dailyForecast(country, city, wf.getLatitude(), wf.getLongitude()));
    }
}

*/
