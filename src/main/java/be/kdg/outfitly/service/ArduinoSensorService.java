package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;

import java.time.LocalDateTime;

public interface ArduinoSensorService {
    ArduinoSensor findByDate(LocalDateTime time);
    ArduinoSensor findByUser(User user, LocalDateTime time);
}
