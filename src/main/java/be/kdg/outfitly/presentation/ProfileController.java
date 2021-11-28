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

import java.util.Objects;

@Controller
@RequestMapping("/profile")
@SessionAttributes("user")
public class ProfileController {
    Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private UserService userService;
    private ClothingService clothingService;

    public ProfileController(UserService userService, ClothingService clothingService) {
        this.userService = userService;
        this.clothingService = clothingService;
    }

    @GetMapping
    public String showProfile(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/changelocation")
    public String changeLocation(Model model, @ModelAttribute("user") User user){
        model.addAttribute("loggedIn", user.getId() != -1);
        logger.debug("User is trying to change their location. Their current location is: " + user.getCity());
        logger.debug("User: " + user);
        model.addAttribute("user", user);
        return "changelocation";
    }

    @PostMapping("/changelocation")
    public String processChangedLocation(@ModelAttribute("user") User user, String country, String city, String streetName, int streetNumber, String apartmentNumber, String zipcode){
        logger.debug(user.getFirstName() + " changed their city to " + city);
        user.setCity(city);
        user.setCountry(country);
        user.setStreetName(streetName);
        user.setStreetNumber(streetNumber);
        user.setApartmentNumber(apartmentNumber);
        user.setZipcode(zipcode);
        userService.update(user);
        return "redirect:/profile";
    }

    @GetMapping("/changepassword")
    public String changePassword(@ModelAttribute("user") User user){
        logger.debug("User: " + user.getPassword());
        logger.debug(user.getFirstName() + " wants to change their password");
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String processChangePassword(@ModelAttribute("user") User user, String verifyPassword, String newPassword){
        logger.debug("Verify password: " + verifyPassword + ", normal password: " + user.getPassword());
        logger.debug("New password: " + newPassword);
        if(Objects.equals(verifyPassword, user.getPassword())){
            logger.debug("User correctly wrote their password");
            user.setPassword(newPassword);
            userService.update(user);
            return "redirect:/profile";
        }else{
            logger.debug("User didn't write their password correctly");
            return "changepassword";
        }
    }

    @GetMapping("/changename")
    public String changeName(Model model, @ModelAttribute("user") User user){
        logger.debug(user.getFirstName() + " is trying to change their name");
        model.addAttribute("user", user);
        return "changename";
    }

    @PostMapping("/changename")
    public String processChangeName(@ModelAttribute("user") User user, String firstName, String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userService.update(user);
        return "redirect:/profile";
    }

    @GetMapping("/changephonenumber")
    public String changePhoneNumber(Model model, @ModelAttribute("user") User user){
        logger.debug(user.getFirstName() + " is trying to change their phonenumber. It's cs currently: " + user.getPhoneNumber());
        model.addAttribute("user", user);
        return "changephonenumber";
    }

    @PostMapping("/changephonenumber")
    public String processChangePhoneNumber(@ModelAttribute("user") User user, String newPhoneNumber){
        user.setPhoneNumber(newPhoneNumber);
        logger.debug("New phone number: " + newPhoneNumber);
        logger.debug("Succesfully changed " + user.getFirstName() + "'s phone number to: " + user.getPhoneNumber());
        userService.update(user);
        return "redirect:/profile";
    }

    @GetMapping("/viewclothing")
    public String viewClothing(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "viewclothing";
    }

    @PostMapping("/viewclothing")
    public String processRemoveClothing(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        clothingService.delete(clothingId);
        return "viewclothing";
    }
}
