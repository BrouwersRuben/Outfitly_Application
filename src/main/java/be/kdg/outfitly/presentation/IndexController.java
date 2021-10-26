package be.kdg.outfitly.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpSession session){
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login";

    }
}
