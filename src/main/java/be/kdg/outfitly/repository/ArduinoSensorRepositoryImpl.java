package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ArduinoSensorRepositoryImpl extends ListRepository<ArduinoSensor> implements ArduinoSensorRepository{
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorRepositoryImpl.class);

    public ArduinoSensorRepositoryImpl(){
        logger.debug("Creating Arduino Sensor repository");
    }
}
