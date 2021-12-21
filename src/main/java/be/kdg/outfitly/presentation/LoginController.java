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

//    @PostMapping
//    public String processLogin(UserDTO userDTO, Model model, @ModelAttribute("user") User user) {
//        List<User> users = userService.read();
//        boolean result = users.stream().anyMatch(userN -> Objects.equals(userN.getEmail(), userDTO.getEmail()) && Objects.equals(userN.getPassword(), userDTO.getPassword()));
//        if (result) {
//            User currentUser = users.stream().filter(userN -> userN.getEmail().equals(userDTO.getEmail())).collect(Collectors.toList()).get(0);
//            user.setClothes(currentUser.getClothes());
//            user.setEmail(currentUser.getEmail());
//            user.setFirstName(currentUser.getFirstName());
//            user.setLastName(currentUser.getLastName());
//            user.setPassword(currentUser.getPassword());
//            user.setId(currentUser.getId());
//            user.setCity(currentUser.getCity());
//            user.setCountry(currentUser.getCountry());
//            user.setApartmentNumber(currentUser.getApartmentNumber());
//            user.setStreetName(currentUser.getStreetName());
//            user.setStreetNumber(currentUser.getStreetNumber());
//            user.setZipcode(currentUser.getZipcode());
//            user.setPhoneNumber(currentUser.getPhoneNumber());
//            return "redirect:/mainpage";
//        } else {
//            model.addAttribute("errorMessage", "Please fill in your correct login details.");
//            return "login";
//        }
//    }

    @GetMapping(params = "error")
    public String errorLogin(Model model){
        model.addAttribute("errorMessage", "Wrong email or password.");
        return "login";
    }

}
