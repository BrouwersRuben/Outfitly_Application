package be.kdg.outfitly.presentation;

import be.kdg.outfitly.service.ArduinoSensorService;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/mainpage")
public class MainPageController {
    private final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final ArduinoSensorService arduinoSensorService;
    private final UserService userService;

    public MainPageController(ArduinoSensorService arduinoSensorService, UserService userService) {
        this.arduinoSensorService = arduinoSensorService;
        this.userService = userService;
    }

    @GetMapping
    public String ShowWeather(Model model){
        //TODO: Show username of current logged in user
        model.addAttribute("arduinoSensorData", arduinoSensorService.findByDate(LocalDateTime.of(2021, 10, 29, 12, 30, 30)));
        model.addAttribute("username", /*userService.getUsername()*/"$placeholder$");
        return "mainpage";
    }
}
