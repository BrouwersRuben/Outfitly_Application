package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/addclothing")
@SessionAttributes("user")
public class AddClothingController {
    private Logger logger = LoggerFactory.getLogger(AddClothingController.class);
    @GetMapping
    public String addClothing(Model model, @ModelAttribute("user") User user){
        Map<String, List<Object>> enumsValues = new HashMap();
        enumsValues.put("type", List.of(ClothingItem.Type.values()));
        enumsValues.put("occasion", List.of(ClothingItem.Occasion.values()));
        enumsValues.put("rainproofness", List.of(ClothingItem.RainProofness.values()));
        enumsValues.put("material", List.of(ClothingItem.Material.values()));
        enumsValues.put("weather", List.of(ClothingItem.Weather.values()));
        //same for all enums of clothing item
        model.addAttribute("enums", enumsValues.entrySet());
        logger.debug("Passing enums: " + enumsValues.entrySet());
        return "addclothing";
    }

    @PostMapping
    public String processClothing(@ModelAttribute("user") User user, String clothingName, String material, String rainproofness, String occasion, String weather, String type){
        logger.debug("User filled in clothing: " + clothingName);
        //Can this be done better?
        ClothingItem.Material materialEnum = ClothingItem.Material.valueOf(material);
        ClothingItem.RainProofness rainproofnessEnum = ClothingItem.RainProofness.valueOf(rainproofness);
        ClothingItem.Occasion occasionEnum = ClothingItem.Occasion.valueOf(occasion);
        ClothingItem.Weather weatherEnum = ClothingItem.Weather.valueOf(weather);
        ClothingItem.Type typeEnum = ClothingItem.Type.valueOf(type);

        ClothingItem newClothingItem = new ClothingItem(clothingName, materialEnum, rainproofnessEnum, occasionEnum, weatherEnum, typeEnum);
        List<ClothingItem> userClothing = new ArrayList<>(user.getClothes());
        userClothing.add(newClothingItem);

        logger.debug(user.getName() + " Added a new clothing item: " + newClothingItem);

        user.setClothes(userClothing);
        return "redirect:/mainpage";
    }
}
