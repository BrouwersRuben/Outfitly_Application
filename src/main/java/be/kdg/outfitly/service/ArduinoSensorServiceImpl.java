package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArduinoSensorServiceImpl implements ArduinoSensorService{
    private final ArduinoSensorRepository arduinoSensorRepository;
    private Logger logger = LoggerFactory.getLogger(ArduinoSensor.class);

    @Autowired
    public ArduinoSensorServiceImpl(ArduinoSensorRepository arduinoSensorRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
    }

    @Override
    public ArduinoSensor findByUser(User user, LocalDateTime time) {
        logger.debug("Find by email: " + user.getEmail());
        List<ArduinoSensor> sensorDatas = arduinoSensorRepository.findAll().stream().filter(sensorData -> sensorData.getEmail().equals(user.getEmail())).collect(Collectors.toList());
        sensorDatas = sensorDatas.stream()
                .filter(sensorData -> sensorData.getTimeOfReading().isAfter(LocalDateTime.now().minusHours(1)))
                .sorted(Comparator.comparing(ArduinoSensor::getTimeOfReading))
                .collect(Collectors.toList());
        if (sensorDatas.size() == 0){
            return null; //TODO: have the AI check if there is a null, if so, just use the API
        } else {
            return sensorDatas.get(0);
        }
    }


    @Override
    public ArduinoSensor saveSensorData(double humidity, double temperature, String email){
        return arduinoSensorRepository.save(new ArduinoSensor(temperature, humidity, email, LocalDateTime.now()));
    }
}
