package be.kdg.outfitly.presentation;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.service.ArduinoSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class ArduinoController {
    private Logger logger = LoggerFactory.getLogger(ArduinoController.class);
    private ArduinoSensorService arduinoSensorService;

    public ArduinoController(ArduinoSensorService arduinoSensorService) {
        this.arduinoSensorService = arduinoSensorService;
    }

    //This REST server is very basic and based of an example Mr. Vochten gave us. We will learn the correct ways and standards of REST servers in programming 2.3
    //http://ipoflaptop/api/sensordata?humidity={double humidity}&temperature={double temperature}&email={String email}
    @GetMapping("/api/sensordata")
    public ArduinoSensor measurement(@RequestParam double humidity, @RequestParam double temperature, @RequestParam String email){
        logger.debug("sensorData: " + humidity + "%, " + temperature + "°C, " + email);
        ArduinoSensor newData = arduinoSensorService.saveSensorData(humidity, temperature, email);
        logger.debug("Sensordata save: " + newData);
        return newData;
    }
}
