package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    private UserService userService;

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/index";
    }

    @GetMapping("/faq")
    public String showFAQ(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/faq";
    }

    @GetMapping("/aboutus")
    public String showAboutUs(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/aboutus";
    }

    @GetMapping("/pricing")
    public String showDoesNotExist(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/pricing";
    }

    @GetMapping("review-jonas")
    public String showReviewJonas(Model model, Principal principal){
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/review-jonas";
    }

    @GetMapping("review-marie")
    public String showReviewMarie(Model model, Principal principal){
        model.addAttribute("loggedIn", principal != null);
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "staticPages/review-marie";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
