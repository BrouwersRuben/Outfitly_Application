package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.*;
import be.kdg.outfitly.service.OutfitSelectorService;
import be.kdg.outfitly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/outfit")
public class OutfitController {

    private final OutfitSelectorService outfitSelector;
    private UserService userService;

    public OutfitController(OutfitSelectorService outfitSelector) {
        this.outfitSelector = outfitSelector;
    }

    @GetMapping
    public String occasionSelector(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user != null);
        model.addAttribute("user", user);
        model.addAttribute("occasions", ClothingItem.Occasion.values());
        return "choose-occasion";
    }

    @PostMapping
    public String occasionForm(ClothingItem.Occasion occasion, Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("clothes", outfitSelector.getSuitableClothes(user, occasion));
        model.addAttribute("types", List.of(ClothingItem.Type.values()));
        model.addAttribute("aiDecision", outfitSelector.getAiDecision());
        return "outfit";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
