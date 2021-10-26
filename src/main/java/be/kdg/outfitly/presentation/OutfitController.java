package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outfit")
public class OutfitController {

    private OutfitSelector outfitSelector;
    private final Logger logger = LoggerFactory.getLogger(OutfitController.class);

    @GetMapping("/choose-occasion")
    public String occasionSelector(Model model){
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        return "choose-occasion";
    }

    @PostMapping("/choose-occasion")
    public String occasionForm(ClothingItem.Occasion occasion){

        //TODO:


        return "outfit";
    }


}
