package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.service.ArduinoSensorService;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/mainpage")
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final ArduinoSensorService arduinoSensorService;
    private final UserService userService;

    private final WeatherForecastService weatherForecastService;

    public MainPageController(ArduinoSensorService arduinoSensorService, UserService userService, WeatherForecastService weatherForecastService) {
        this.arduinoSensorService = arduinoSensorService;
        this.userService = userService;
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping
    public String ShowWeather(Model model){
        //TODO: Show username of current logged in user
        model.addAttribute("arduinoSensorData", arduinoSensorService.findByDate(LocalDateTime.of(2021, 10, 29, 12, 30, 30)));
        model.addAttribute("username", /*userService.getUsername()*/"$placeholder$");
        model.addAttribute("weatherForecastData", weatherForecastService.findByDate(LocalDateTime.of(2021, 10, 29, 12, 30, 30)));
        return "mainpage";
    }
}