package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.ArduinoSensorService;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user/mainpage")
public class MainPageController {
    private final ArduinoSensorService arduinoSensorService;
    private final UserService userService;

    private final WeatherForecastService weatherForecastService;

    public MainPageController(ArduinoSensorService arduinoSensorService, UserService userService, WeatherForecastService weatherForecastService) {
        this.arduinoSensorService = arduinoSensorService;
        this.userService = userService;
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping
    public String ShowWeather(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
        model.addAttribute("arduinoSensorData", arduinoSensorService.findByUser(user));
        model.addAttribute("username", user.getName());
        model.addAttribute("weatherForecastData", weatherForecastService.findByCountryAndCity(user.getCountryCode(), user.getCity()));
        return "mainpage";
    }
}
