package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.ArduinoSensorService;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/user/main-page")
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final UserService userService;
    private final WeatherForecastService weatherForecastService;
    private final ArduinoSensorService arduinoSensorService;

    public MainPageController(UserService userService, WeatherForecastService weatherForecastService, ArduinoSensorService arduinoSensorService) {
        this.userService = userService;
        this.weatherForecastService = weatherForecastService;
        this.arduinoSensorService = arduinoSensorService;
    }

    @GetMapping
    public String showDailyForecast(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user != null);
        model.addAttribute("user", user);

        model.addAttribute("username", user.getName());
        model.addAttribute("city", user.getCity());
        model.addAttribute("dailyWeatherForecastData", weatherForecastService.findByCountryAndCity(user.getCountryCode(), user.getCity()));
        model.addAttribute("sensorWeatherForecastData", arduinoSensorService.findByUser(user));
        return "main-page";
    }
}
