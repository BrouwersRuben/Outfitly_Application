package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outfit")
public class OutfitController {

    private OutfitSelector outfitSelector;

    @GetMapping("/choose-occasion")
    public String occasionSelector(Model model){
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        return "selectoccasion";
    }

    @PostMapping("/choose-occasion")
    public String occasionForm(ClothingItem.Occasion occasion){

        //TODO:

//        outfitSelector = new OutfitSelector();

        return "outfitpage";
    }


}
