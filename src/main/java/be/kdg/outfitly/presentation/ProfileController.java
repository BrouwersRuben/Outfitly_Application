package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.ClothingDTO;
import be.kdg.outfitly.presentation.dto.profileChanges.NameDTO;
import be.kdg.outfitly.presentation.dto.profileChanges.PhoneNumberDTO;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.presentation.dto.profileChanges.LocationDTO;
import be.kdg.outfitly.presentation.dto.profileChanges.PasswordDTO;
import be.kdg.outfitly.service.ClothingService;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
    Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private UserService userService;
    private ClothingService clothingService;

    @Autowired
    public ProfileController(UserService userService, ClothingService clothingService) {
        this.userService = userService;
        this.clothingService = clothingService;
    }

    @GetMapping
    public String showProfile(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/changelocation")
    public String changeLocation(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedIn", user.getId() != -1);
        logger.debug("User is trying to change their location. Their current location is: " + user.getCity());
        logger.debug("User: " + user);
        model.addAttribute("user", user);
        model.addAttribute("codes", Locale.getISOCountries());
        model.addAttribute("locationDTO", new LocationDTO());
        return "changelocation";
    }

    @PostMapping("/changelocation")
    public String processChangedLocation(Principal principal, @Valid @ModelAttribute("locationDTO") LocationDTO locationDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "changelocation";
        } else {
//            logger.debug(user.getFirstName() + " changed their city to " + city);
            user.setCity(locationDTO.getCity());
            user.setCountry(locationDTO.getCountry());
            user.setCountryCode(locationDTO.getCountryCode());
            user.setStreetName(locationDTO.getStreetName());
            user.setStreetNumber(locationDTO.getStreetNumber());
            user.setApartmentNumber(locationDTO.getApartmentNumber());
            user.setZipcode(locationDTO.getZipcode());
            userService.update(user);
            return "redirect:/user/profile";
        }
    }

    @GetMapping("/changepassword")
    public String changePassword(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("passwordDTO", new PasswordDTO());
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
//        logger.debug(user.getFirstName() + " wants to change their password");
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String processChangePassword(Model model, Principal principal, @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        logger.debug("currentPassword: " + user.getPassword());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "changepassword";
        } else {
            logger.debug("currentPasswordDTO: " + passwordDTO.getCurrentPassword());
            logger.debug("newPasswordDTO: " + passwordDTO.getNewPassword());
            if (passwordDTO.getCurrentPassword().equals(user.getPassword())) {
//                logger.debug("User correctly wrote their password");
                user.setPassword(passwordDTO.getCurrentPassword());
                userService.update(user);
                return "redirect:/user/profile";
            } else {
//                logger.debug("User didn't write their password correctly");
                model.addAttribute("errorMessage", "This password is incorrect");
                return "changepassword";
            }
        }
    }

    @GetMapping("/changename")
    public String changeName(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        logger.debug(user.getFirstName() + " is trying to change their name");
        model.addAttribute("user", user);
        model.addAttribute("nameDTO", new NameDTO());
        return "changename";
    }

    @PostMapping("/changename")
    public String processChangeName(Principal principal, @Valid @ModelAttribute("nameDTO") NameDTO nameDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "changename";
        } else {
            user.setFirstName(nameDTO.getFirstName());
            user.setLastName(nameDTO.getLastName());
            userService.update(user);
            return "redirect:/user/profile";
        }
    }

    @GetMapping("/changephonenumber")
    public String changePhoneNumber(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        logger.debug(user.getFirstName() + " is trying to change their phonenumber. It's cs currently: " + user.getPhoneNumber());
        model.addAttribute("user", user);
        model.addAttribute("phoneNumberDTO", new PhoneNumberDTO());
        return "changephonenumber";
    }

    @PostMapping("/changephonenumber")
    public String processChangePhoneNumber(Principal principal, @Valid @ModelAttribute("phoneNumberDTO") PhoneNumberDTO phoneNumberDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "changephonenumber";
        } else {
            user.setPhoneNumber(phoneNumberDTO.getNewPhoneNumber());
            logger.debug("New phone number: " + phoneNumberDTO.getNewPhoneNumber());
            logger.debug("Succesfully changed " + user.getFirstName() + "'s phone number to: " + user.getPhoneNumber());
            userService.update(user);
            return "redirect:/user/profile";
        }
    }

    @GetMapping("/viewclothing")
    public String viewClothing(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "viewclothing";
    }

    @PostMapping("/viewclothing")
    public String processRemoveClothing(@ModelAttribute("clothingDTO") ClothingDTO clothingDTO, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        logger.warn(clothingDTO.toString());

        ClothingItem clothingItemToRemove = clothingService.findById(clothingDTO.getID());

        clothingService.delete(clothingDTO.getID());
        List<ClothingItem> newClothingList = clothingService.read();
        user.setClothes(newClothingList);
        userService.update(user);
        model.addAttribute("user", user);

        User userFromRepo = userService.findById(user.getId());
        List<ClothingItem> clothes = new ArrayList<>(userFromRepo.getClothes());

        clothes.remove(clothingItemToRemove);

        userFromRepo.setClothes(clothes);
        userService.update(userFromRepo);
        return "redirect:/user/profile/viewclothing";
    }
}
