package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;

import java.time.LocalDateTime;

public interface ArduinoSensorService {
    ArduinoSensor findByUser(User user);
}
