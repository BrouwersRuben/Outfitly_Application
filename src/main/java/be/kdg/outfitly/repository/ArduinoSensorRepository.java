package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ArduinoSensorRepository extends ListRepository<ArduinoSensor>{
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorRepository.class);

    public ArduinoSensorRepository(){
        logger.debug("Creating Arduino Sensor repository");
    }
}
