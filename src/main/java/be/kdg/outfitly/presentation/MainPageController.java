package be.kdg.outfitly.presentation;

import be.kdg.outfitly.service.ArduinoSensorService;
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

    public MainPageController(ArduinoSensorService arduinoSensorService) {
        this.arduinoSensorService = arduinoSensorService;
    }

    @GetMapping
    public String ShowWeather(Model model){
        model.addAttribute("arduinoSensorData", arduinoSensorService.findByDate(LocalDateTime.now()));
        return "mainpage";
    }
}
