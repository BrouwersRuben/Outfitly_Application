package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.ClothingItem;

import java.util.List;

public interface ArduinoSensorRepository {
    List<ArduinoSensor> read();
    ArduinoSensor findById(int id);
    ArduinoSensor create(ArduinoSensor arduinoSensor);
}
