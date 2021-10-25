package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loadLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(String email, String password, Model model){
        logger.debug("Username entered: " + email);
        logger.debug("Password entered: " + password);
        List<User> users = userRepository.read();
        //will take a long time with a lot of entries --> database sql
        boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password));

        if(result){
            logger.debug("Filled in correct details");
            return "redirect:/mainpage";
        }else{
            logger.debug("Wrong details");
            model.addAttribute("errorMessage", "Please fill in your correct login details.");
            return "login";
        }
    }
}
