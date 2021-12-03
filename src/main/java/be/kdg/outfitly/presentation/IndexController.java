package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private UserService userService;

    @GetMapping("/faq")
    public String showFAQ(Model model , Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if(principal!=null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "faq";
    }

    @GetMapping
    public String indexPage(Model model , Principal principal){
        model.addAttribute("loggedIn", principal != null);
        if(principal!=null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/pricing")
    public String showDoesNotExist(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if(principal!=null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "pricing";
    }




    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
