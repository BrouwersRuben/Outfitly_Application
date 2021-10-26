package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping
    public String register(){
        return "register";
    }

    @PostMapping
    public String processRegister(UserDTO userDTO, Model model){
        String name = userDTO.getName();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();

        List<User> users = userRepository.read();

        boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()));
        if(result){
            logger.debug("User tried creating a new account with an existing email");
            model.addAttribute("errorMessage", "That email already exists");
            return "register";
        }else{
            logger.debug("New email has been created");
            //No clothes yet
            userRepository.create(new User(userDTO.getEmail(),userDTO.getPassword(), userDTO.getName(), null));
            return "redirect:/login";
        }
    }
}
