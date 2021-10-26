package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showLogin(Model model) {
        return "login";
    }

    @PostMapping
    public String processLogin(UserDTO userDTO, Model model){
        logger.debug("Username entered: " + userDTO.getEmail());
        logger.debug("Password entered: " + userDTO.getPassword());
        List<User> users = userRepository.read();
        //will take a long time with a lot of entries --> database
        boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()) && Objects.equals(user.getPassword(), userDTO.getPassword()));
        if(result){
            logger.debug("Filled in correct details");
            //get name to path into mainpage
            String name = users.stream().filter(user -> user.getEmail().equals(userDTO.getEmail())).map(User::getName).reduce("",(curr, acc)->curr+acc);
            logger.debug("User: " + name);
            return "redirect:/mainpage?name=" + name;
        }else{
            logger.debug("Wrong details");
            model.addAttribute("errorMessage", "Please fill in your correct login details.");
            return "login";
        }
    }
}
