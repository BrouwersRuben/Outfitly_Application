package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutfitController {

    private OutfitSelector outfitSelector;

    @GetMapping("/occasion")
    public String occasionSelector(){
        return "selectoccasion";
    }

    @PostMapping("/occasion")
    public String occasionForm(ClothingItem.Occasion occasion){

        //TODO:

//        outfitSelector = new OutfitSelector();

        return "outfitpage";
    }


}
