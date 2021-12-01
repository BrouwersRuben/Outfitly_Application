package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/outfit")
public class OutfitController {

    private OutfitSelector outfitSelector;
    private final Logger logger = LoggerFactory.getLogger(OutfitController.class);
    private WeatherForecastService weatherForecastService;
    private UserService userService;

    public OutfitController(WeatherForecastService weatherForecastService) {
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
        WeatherForecast weatherForecast = weatherForecastService.findByCountryAndCity(user.getCountry(), user.getCity());
        logger.debug("Weather forecast from the API: " + weatherForecast);
        outfitSelector = new OutfitSelector(weatherForecast, user, occasion);
        model.addAttribute("clothes", outfitSelector.getSuitableClothesMap());
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
//        logger.debug("Logger output: " + outfitSelector.getAiDecision().toString());
        return "outfit";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
