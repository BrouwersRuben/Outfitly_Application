package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ArduinoSensorServiceImpl implements ArduinoSensorService{
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorServiceImpl.class);
    private final ArduinoSensorRepository arduinoSensorRepository;

    @Autowired
    public ArduinoSensorServiceImpl(ArduinoSensorRepository arduinoSensorRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
    }

//    @Override
//    public List<ArduinoSensor> showSensorData(){
//        logger.debug("Showing arduino sensor data");
//        return arduinoSensorRepository.read();
//    }

    @Override
    public ArduinoSensor showSensorDataByDate(LocalDateTime time){
        logger.debug("Showing sensorData of date: " + time);
        return arduinoSensorRepository.findByDate(time);
    }
}
