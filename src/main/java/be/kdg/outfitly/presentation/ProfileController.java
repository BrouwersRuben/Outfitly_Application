package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.ClothingDTO;
import be.kdg.outfitly.presentation.dto.profileChanges.*;
import be.kdg.outfitly.service.ClothingService;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
//TODO: God Class?

    Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final UserService userService;
    private final ClothingService clothingService;

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
        model.addAttribute("user", user);
        model.addAttribute("codes", Locale.getISOCountries());
        model.addAttribute("locationDTO", new LocationDTO());
        return "changelocation";
    }

    @PostMapping("/changelocation")
    public String processChangedLocation(Principal principal, @Valid @ModelAttribute("locationDTO") LocationDTO locationDTO, BindingResult errors, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("codes", Locale.getISOCountries());
        model.addAttribute("user", user);
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "changelocation";
        } else {
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
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String processChangePassword(Model model, Principal principal, @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "user/profile/changepassword";
        } else {
            if (passwordDTO.getCurrentPassword().equals(user.getPassword())) {
                user.setPassword(passwordDTO.getCurrentPassword());
                userService.update(user);
                return "redirect:/user/profile";
            } else {
                //TODO: Bean validation
                model.addAttribute("errorMessage", "This password is incorrect");
                return "changepassword";
            }
        }
    }

    @GetMapping("/changename")
    public String changeName(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("nameDTO", new NameDTO());
        return "changename";
    }

    @PostMapping("/changename")
    public String processChangeName(Principal principal, @Valid @ModelAttribute("nameDTO") NameDTO nameDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "user/profile/changename";
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
        model.addAttribute("user", user);
        model.addAttribute("phoneNumberDTO", new PhoneNumberDTO());
        return "changephonenumber";
    }

    @PostMapping("/changephonenumber")
    public String processChangePhoneNumber(Principal principal, @Valid @ModelAttribute("phoneNumberDTO") PhoneNumberDTO phoneNumberDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "user/profile/changephonenumber";
        } else {
            user.setPhoneNumber(phoneNumberDTO.getNewPhoneNumber());
            userService.update(user);
            return "redirect:/user/profile";
        }
    }

    @GetMapping("/viewclothing")
    public String viewClothing(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("clothingDTO", new ClothingDTO());

        return "viewclothing";
    }

    @PostMapping("/viewclothing")
    public String processRemoveClothing(@ModelAttribute("clothingDTO") ClothingDTO clothingDTO, Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());

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

    @GetMapping("/changewashingresetday")
    public String changeWashingDay(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("washingDayDTO", new WashingDayDTO());
        return "changewashingresetday";
    }

    @PostMapping("/changewashingresetday")
    public String processChangeWashingDay(Model model, Principal principal, @Valid @ModelAttribute("washingDayDTO") WashingDayDTO washingDayDTO, BindingResult errors) {
        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            model.addAttribute("user", user);
            return "changewashingresetday";
        } else {
            user.setWashingResetDay(washingDayDTO.getNewWashingResetDay());
            userService.update(user);
            return "redirect:/user/profile";
        }
    }
}
