package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.*;
import be.kdg.outfitly.presentation.dto.ClothingDTO;
import be.kdg.outfitly.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/outfit")
public class OutfitController {

    private final OutfitSelectorService outfitSelector;
    private final ClothingService clothingService;
    private final UserService userService;

    public OutfitController(OutfitSelectorService outfitSelector, UserService userService, ClothingService clothingService) {
        this.outfitSelector = outfitSelector;
        this.userService = userService;
        this.clothingService = clothingService;
    }

    @GetMapping
    public String occasionSelector(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        return "dynamicPages/choose-occasion";
    }

    @PostMapping
    public String occasionForm(ClothingItem.Occasion occasion, Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("clothes", outfitSelector.getSuitableClothesGroupedByType(user, occasion));
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
        model.addAttribute("occasion", occasion.getName());
        return "dynamicPages/outfit";
    }

    @PostMapping(params = {"putInWash"})
    public String putInWash(Principal principal, Model model, @ModelAttribute("occasion") String occasionName, @ModelAttribute("clothingDTO") ClothingDTO clothingDTO){
        User user = userService.findByEmail(principal.getName());
        ClothingItem toPutInWash = clothingService.findById(clothingDTO.getID());
        clothingService.putInWash(toPutInWash);
        model.addAttribute("clothes", outfitSelector.getSuitableClothesGroupedByType(user, ClothingItem.Occasion.valueOf(occasionName.toUpperCase())));
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
        return "dynamicPages/outfit";
    }

}
