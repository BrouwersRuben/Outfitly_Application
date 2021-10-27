package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/faq")
    public String showFAQ(Model model , @ModelAttribute("user") User user) {
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);

        return "faq";
    }

    @GetMapping
    public String indexPage(Model model , @ModelAttribute("user") User user){
        model.addAttribute("loggedIn", user.getId() != -1);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/...")
    public String showDoesNotExist(Model model) {
        return "doesnotexist";
    }

    @ModelAttribute("user")
    public User user() {
        User user = new User();
        user.setId(-1);
        return user;
    }
}
