package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JavaCollections")
public class ArduinoSensorRepositoryCollectionsImpl extends ListRepository<ArduinoSensor> implements ArduinoSensorRepository{
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorRepositoryCollectionsImpl.class);

    public ArduinoSensorRepositoryCollectionsImpl(){
        logger.debug("Creating Arduino Sensor repository (Collections)");
    }
}
