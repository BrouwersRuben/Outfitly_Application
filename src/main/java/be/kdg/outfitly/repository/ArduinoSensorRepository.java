package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArduinoSensorRepository extends JpaRepository<ArduinoSensor, Integer> {
}