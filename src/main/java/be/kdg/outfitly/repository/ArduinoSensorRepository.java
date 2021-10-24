package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArduinoSensorRepository extends ListRepository<ArduinoSensor>{
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorRepository.class);
    protected List<ArduinoSensor> sensorsData = new ArrayList<>();

    public ArduinoSensorRepository(){
        logger.debug("Creating Arduino Sensor repository");
    }

    public ArduinoSensor findByDate(LocalDateTime time) {
        return sensorsData.stream().filter(data -> data.getTimeOfReading().equals(time)).findFirst().get();
    }
}
