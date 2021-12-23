package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController {
    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLogin(Model model, @ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping(params = "error")
    public String errorLogin(Model model){
        model.addAttribute("errorMessage", "Wrong email or password.");
        return "login";
    }

}
