package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.*;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user/choose-occasion")
public class OccasionController {
    private Logger logger = LoggerFactory.getLogger(OccasionController.class);
    private UserService userService;
    private WeatherForecastService weatherForecastService;
    private OutfitSelector outfitSelector;

    public OccasionController(UserService userService, WeatherForecastService weatherForecastService) {
        this.userService = userService;
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping
    public String occasionSelector(Model model, Principal principal) {
        logger.debug("principal" + principal.toString());
        logger.debug("principal name" + principal.getName());
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user != null);
        model.addAttribute("user", user);
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        logger.debug(user.getId() + "");
        logger.debug(user.getName() + "");
        return "choose-occasion";
    }

    @PostMapping
    public String occasionForm(ClothingItem.Occasion occasion,
                               Model model,
                               Principal principal) {

//        outfitSelector = new OutfitSelector(WeatherForecast.randomForecast(), user, occasion);
        User user = userService.findByEmail(principal.getName());
        logger.debug("User for the outfit: "+user.toString());
        //API
        WeatherForecast weatherForecast = weatherForecastService.findByCountryAndCity(user.getCountryCode(), user.getCity());
        logger.debug("Weather forecast from the API: " + weatherForecast);
        //Arduino
        //ArduinoSensor arduinoSensor = arduinoSensorService.findByUser(userService.findByEmail(principal.getName()), LocalDateTime.now());
        ArduinoSensor arduinoSensor = new ArduinoSensor(10, 20, LocalDateTime.now());
        logger.debug("Weather forecast from the Arduino: " + arduinoSensor);
        outfitSelector = new OutfitSelector(weatherForecast, arduinoSensor, user, occasion);
        model.addAttribute("clothes", outfitSelector.getSuitableClothesMap());
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
//        logger.debug("Logger output: " + outfitSelector.getAiDecision().toString());
        return "/user/outfit";
    }
}
