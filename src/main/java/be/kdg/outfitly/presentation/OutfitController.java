package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/outfit")
@SessionAttributes("user")
public class OutfitController {

    private OutfitSelector outfitSelector;
    private final Logger logger = LoggerFactory.getLogger(OutfitController.class);
    private WeatherForecastService weatherForecastService;

    public OutfitController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping
    public String occasionSelector(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        logger.debug(user.getId() + "");
        logger.debug(user.getName() + "");
        return "choose-occasion";
    }

    @PostMapping
    public String occasionForm(ClothingItem.Occasion occasion,
                               @ModelAttribute("user") User user,
                               Model model) {

//        outfitSelector = new OutfitSelector(WeatherForecast.randomForecast(), user, occasion);
        WeatherForecast weatherForecast = weatherForecastService.findByDate(LocalDateTime.of(2021, 10, 29, 12, 30, 30));
        logger.debug("Weather forecast from the API: " + weatherForecast);
        outfitSelector = new OutfitSelector(weatherForecast, user, occasion);
        model.addAttribute("clothes", outfitSelector.getSuitableClothesMap());
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
//        logger.debug("Logger output: " + outfitSelector.getAiDecision().toString());
        return "outfit";
    }


}
