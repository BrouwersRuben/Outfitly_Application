package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.ClothingService;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private ClothingService clothingService;


    public AddClothingController(UserService userService, ClothingService clothingService) {
        this.userService = userService;
        this.clothingService = clothingService;
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
    public String processClothing(Principal principal, String clothingName, ClothingItem.Material material, ClothingItem.RainProofness rainproofness, ClothingItem.Occasion occasion, ClothingItem.Weather weather, ClothingItem.Type type, MultipartFile photo){
        logger.debug("User filled in clothing: " + clothingName);
        User user = userService.findByEmail(principal.getName());


        ClothingItem newClothingItem = new ClothingItem(clothingName, material, rainproofness, occasion, weather, type);
        try {
            newClothingItem.setPhoto(photo.getBytes());
            newClothingItem.setPhotoMIMEType(photo.getContentType());
            logger.debug("test encoded photo: "+newClothingItem.getPhotoEncoded());
        } catch(IOException ioe){
            logger.error("Uploading the photo failed.");
        }
        //quick fix, gives "java.lang.UnsupportedOperationException: null" error
        //with ex. userClothing.getClothes().add(..)
        //or addClothing method inside of User
//        List<ClothingItem> userClothing = new ArrayList<>(user.getClothes());
//        userClothing.add(newClothingItem);
        User userFromRepository = userService.findById(user.getId());
//        userFromRepository.setClothes(userClothing);

        logger.debug(user.getName() + " Added a new clothing item: " + newClothingItem);

//        user.setClothes(userClothing);

        //newClothingItem.setUser(userFromRepository);
        //clothingService.create(newClothingItem);

        List<ClothingItem> userClothing = new ArrayList<>(userFromRepository.getClothes());
        userClothing.add(newClothingItem);
        userFromRepository.setClothes(userClothing);
        userService.update(userFromRepository);
        newClothingItem.setUser(userFromRepository);
        clothingService.create(newClothingItem);

        logger.debug(user.getName() + " Added a new clothing item: " + newClothingItem);

        user.setClothes(userClothing);
        return "redirect:/user/mainpage";
    }
}
