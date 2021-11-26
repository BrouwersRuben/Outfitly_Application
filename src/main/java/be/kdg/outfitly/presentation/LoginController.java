package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLogin(Model model, @ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping
    public String processLogin(UserDTO userDTO, Model model, @ModelAttribute("user") User user) {
        logger.debug("Username entered: " + userDTO.getEmail());
        logger.debug("Password entered: " + userDTO.getPassword());
        List<User> users = userService.read();
        //will take a long time with a lot of entries --> database
        boolean result = users.stream().anyMatch(userN -> Objects.equals(userN.getEmail(), userDTO.getEmail()) && Objects.equals(userN.getPassword(), userDTO.getPassword()));
        if (result) {
            logger.debug("Filled in correct details");
            //get name to path into mainpage
            User currentUser = users.stream().filter(userN -> userN.getEmail().equals(userDTO.getEmail())).collect(Collectors.toList()).get(0);
//          user.setId(5);
            user.setClothes(currentUser.getClothes());
            user.setEmail(currentUser.getEmail());
            user.setFirstName(currentUser.getFirstName());
            user.setLastName(currentUser.getLastName());
            //user.setName(currentUser.getName());
            user.setPassword(currentUser.getPassword());
            user.setId(currentUser.getId());
            user.setCity(currentUser.getCity());

            //name = firstName + surname
            String name = users.stream().filter(userN -> userN.getEmail().equals(userDTO.getEmail())).map(User::getName).reduce("", (curr, acc) -> curr + acc);
            logger.debug("User: " + name);
            return "redirect:/mainpage";
        } else {
            logger.debug("Wrong details");
            model.addAttribute("errorMessage", "Please fill in your correct login details.");
            return "login";
        }
    }

    @ModelAttribute("user")
    public User user() {
        User user = new User();
        user.setId(-1);
        return user;
    }

}
