package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@SessionAttributes("user")
public class ProfileController {
    Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showProfile(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("name",user.getFirstName());
        return "profile";
    }

    @GetMapping("/changelocation")
    public String changeLocation(Model model, @ModelAttribute("user") User user){
        logger.debug("User is trying to change their location. Their current location is: " + user.getCity());
        model.addAttribute("location",user.getCity());
        return "changelocation";
    }

    @PostMapping("/changelocation")
    public String processChangedLocation(@ModelAttribute("user") User user, String city){
        logger.debug("User changed their location from " + user.getCity() + " to " + city);
        user.setCity(city);
        return "redirect:/profile";
    }
}
