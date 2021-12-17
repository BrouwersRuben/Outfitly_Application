package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
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
    public ArduinoSensor findByDate(LocalDateTime time) {
        return arduinoSensorRepository.findAll().stream().filter(sensorData -> sensorData.getTimeOfReading().equals(time)).findFirst().get();
    }

    @Override
    public ArduinoSensor findByUser(User user, LocalDateTime time) {
        return arduinoSensorRepository.findAll().stream().filter(sensorData -> sensorData.equals(user.getSensorData().stream().filter(arduinoSensor -> arduinoSensor.getTimeOfReading().equals(time)))).findFirst().orElse(null);
    }
}
