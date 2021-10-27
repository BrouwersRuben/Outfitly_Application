package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/outfit")
@SessionAttributes("user")
public class OutfitController {

    private OutfitSelector outfitSelector;
    private final Logger logger = LoggerFactory.getLogger(OutfitController.class);

    @GetMapping("/choose-occasion")
    public String occasionSelector(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("loggedin", user.getId() != -1);
        model.addAttribute("user", user);
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        logger.debug(user.getId() + "");
        logger.debug(user.getName() + "");
        return "choose-occasion";
    }

    @PostMapping("/choose-occasion")
    public String occasionForm(ClothingItem.Occasion occasion,
                               @ModelAttribute("user") User user,
                               Model model) {

        outfitSelector = new OutfitSelector(WeatherForecast.randomForecast(), user, occasion);

        model.addAttribute("clothes", outfitSelector.getSuitableClothesMap().entrySet());
        return "outfit";
    }


}
