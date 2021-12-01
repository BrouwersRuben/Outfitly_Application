package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/logout")
public class LogoutController {

//    @GetMapping
//    public String logout(@ModelAttribute("user") User user){
//        user.setId(-1);
//        return "logout";
//    }

}
