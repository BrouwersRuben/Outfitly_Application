package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;

import java.time.LocalDateTime;

public interface ArduinoSensorService {
    ArduinoSensor findByDate(LocalDateTime time);
}
