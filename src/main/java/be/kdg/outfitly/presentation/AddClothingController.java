package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/addclothing")
public class AddClothingController {
    private final Logger logger = LoggerFactory.getLogger(AddClothingController.class);
    private UserService userService;

    public AddClothingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String addClothing(Model model, Principal principal){

        User user = userService.findByEmail(principal.getName());

        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);

        Map<String, List<Object>> enumsValues = new HashMap<>();
        enumsValues.put("type", List.of(ClothingItem.Type.values()));
        enumsValues.put("occasion", List.of(ClothingItem.Occasion.values()));
        enumsValues.put("rainproofness", List.of(ClothingItem.RainProofness.values()));
        enumsValues.put("material", List.of(ClothingItem.Material.values()));
        enumsValues.put("weather", List.of(ClothingItem.Weather.values()));

        model.addAttribute("enums", enumsValues.entrySet());
        logger.debug("Passing enums: " + enumsValues.entrySet());
        return "addclothing";
    }

    //Automatically gets converted to enum
    @PostMapping
    public String processClothing(Principal principal, String clothingName, ClothingItem.Material material, ClothingItem.RainProofness rainproofness, ClothingItem.Occasion occasion, ClothingItem.Weather weather, ClothingItem.Type type){
        logger.debug("User filled in clothing: " + clothingName);
        User user = userService.findByEmail(principal.getName());


        ClothingItem newClothingItem = new ClothingItem(clothingName, material, rainproofness, occasion, weather, type);
        //quick fix, gives "java.lang.UnsupportedOperationException: null" error
        //with ex. userClothing.getClothes().add(..)
        //or addClothing method inside of User
        List<ClothingItem> userClothing = new ArrayList<>(user.getClothes());
        userClothing.add(newClothingItem);
        User userFromRepository = userService.findBytId(user.getId());
        userFromRepository.setClothes(userClothing);

        logger.debug(user.getName() + " Added a new clothing item: " + newClothingItem);

        user.setClothes(userClothing);
        return "redirect:/user/mainpage";
    }
}
