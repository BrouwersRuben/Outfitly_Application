package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/user/weather-forecast")
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final UserService userService;
    private final WeatherForecastService weatherForecastService;

    public MainPageController(UserService userService, WeatherForecastService weatherForecastService) {
        this.userService = userService;
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping
    public String showDailyForecast(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user != null);
        model.addAttribute("user", user);

        model.addAttribute("username", user.getName());
        model.addAttribute("city", user.getCity());
        model.addAttribute("dailyWeatherForecastData", weatherForecastService.findByCountryAndCity(user.getCountryCode(), user.getCity()));
        return "weather-forecast";
    }
}
