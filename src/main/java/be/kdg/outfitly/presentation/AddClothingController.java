package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.Photo;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.exceptions.ClothingPictureTooLargeException;
import be.kdg.outfitly.service.ClothingService;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private final UserService userService;
    private final ClothingService clothingService;


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
        return "addclothing";
    }

    //Automatically gets converted to enum
    @PostMapping
    public String processClothing(Principal principal, String clothingName, ClothingItem.Material material, ClothingItem.RainProofness rainproofness, ClothingItem.Occasion occasion, ClothingItem.Weather weather, ClothingItem.Type type, MultipartFile photo){
        User user = userService.findByEmail(principal.getName());

        ClothingItem newClothingItem = new ClothingItem(clothingName, material, rainproofness, occasion, weather, type, false); //upon creation, washingcycle has been set to false
        try {
            Photo newPhoto = new Photo(photo.getBytes(), photo.getContentType());
            newClothingItem.setPhoto(newPhoto);
        } catch(IOException ioe){
            logger.error("IO exception");
            return "redirect:/user/addclothing";
        }

        newClothingItem.setUser(user);
        clothingService.create(newClothingItem);

        List<ClothingItem> userClothing = new ArrayList<>(user.getClothes());
        userClothing.add(newClothingItem);
        user.setClothes(userClothing);
        userService.update(user);

        user.setClothes(userClothing);
        return "redirect:/user/mainpage";
    }

    @ExceptionHandler(ClothingPictureTooLargeException.class)
    public ModelAndView handleError(Principal principal, HttpServletRequest req, ClothingPictureTooLargeException exception) {
        User user = userService.findByEmail(principal.getName());
        logger.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loggedIn", user.getId() != -1);
        modelAndView.addObject("user", user);
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("clothingpictureerror");
        return modelAndView;
    }
}
