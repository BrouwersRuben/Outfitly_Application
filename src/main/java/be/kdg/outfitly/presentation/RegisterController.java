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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String register(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping
    //TODO: Bean Validation
    public String processRegister(Model model, @Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "register";

        } else {
            List<User> users = userService.read();
            boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()));
            if(result){
                model.addAttribute("errorMessage", "That email already exists");
                return "register";
            } else {
                //No clothes yet
                // TODO: Make it accept userDTO object
                userService.create(userDTO.getEmail(),userDTO.getPassword(),userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber(), userDTO.getCountry(), userDTO.getCity(), userDTO.getStreetName(), Integer.parseInt(userDTO.getStreetNumber()), userDTO.getApartmentNumber(), userDTO.getZipcode(), new ArrayList<>());
                return "redirect:/login";
            }
        }
    }
}
