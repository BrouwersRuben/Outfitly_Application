package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.*;
import be.kdg.outfitly.service.ArduinoSensorService;
import be.kdg.outfitly.service.ClothingService;
import be.kdg.outfitly.service.UserService;
import be.kdg.outfitly.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user/outfit")
public class OutfitController {

    private OutfitSelector outfitSelector;
    private final Logger logger = LoggerFactory.getLogger(OutfitController.class);
    private WeatherForecastService weatherForecastService;
    private ArduinoSensorService arduinoSensorService;
    private ClothingService clothingService;
    private UserService userService;

    public OutfitController(WeatherForecastService weatherForecastService, ArduinoSensorService arduinoSensorService) {
        this.weatherForecastService = weatherForecastService;
        this.arduinoSensorService = arduinoSensorService;
    }

    @PostMapping
    public String putInWash(Principal principal, Model model, @ModelAttribute("id") int id){
        User user = userService.findByEmail(principal.getName());
        ClothingItem toPutInWash = clothingService.findById(id);
        clothingService.putInWash(toPutInWash);
        return "outfit";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
