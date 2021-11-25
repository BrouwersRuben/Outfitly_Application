package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.repository.UserRepository;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String register(){
        return "register";
    }

    @PostMapping
    //TODO: Bean Validation
    public String processRegister(UserDTO userDTO, Model model){

        List<User> users = userService.read();

        //TODO: Bean validation change this? SQL?
        boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()));
        if(result){
            logger.debug("User tried creating a new account with an existing email");
            model.addAttribute("errorMessage", "That email already exists");
            return "register";
        }else{
            logger.debug("New email has been created");
            //No clothes yet
            userService.create(userDTO.getEmail(),userDTO.getPassword(), userDTO.getName(), userDTO.getLocation(), new ArrayList<>());
            return "redirect:/login";
        }
    }
}
