package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArduinoSensorServiceImpl implements ArduinoSensorService{
    private final ArduinoSensorRepository arduinoSensorRepository;

    @Autowired
    public ArduinoSensorServiceImpl(ArduinoSensorRepository arduinoSensorRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
    }

    @Override
    public ArduinoSensor findByUser(User user) {
        //TODO: Has to be changed
        return new ArduinoSensor(3, 80, LocalDateTime.now());
    }
}
