package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArduinoSensorServiceImpl implements ArduinoSensorService{
    private final ArduinoSensorRepository arduinoSensorRepository;
    private final Logger logger = LoggerFactory.getLogger(ArduinoSensor.class);

    @Autowired
    public ArduinoSensorServiceImpl(ArduinoSensorRepository arduinoSensorRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
    }


    @Override
    public ArduinoSensor findByUser(User user) {
        logger.debug("Find by email: " + user.getEmail());
        List<ArduinoSensor> sensorDatas = arduinoSensorRepository.findAll().stream().filter(sensorData -> sensorData.getEmail().equals(user.getEmail())).collect(Collectors.toList());
        sensorDatas = sensorDatas.stream()
                .filter(sensorData -> sensorData.getTimeOfReading().isAfter(LocalDateTime.now().minusHours(1)))
                .sorted(Comparator.comparing(ArduinoSensor::getTimeOfReading)) //What does this line do?
                .collect(Collectors.toList());
        if (sensorDatas.size() == 0){ //Get the latest one
            return null;
        } else {
            return sensorDatas.get(0);
        }
    }


    @Override
    public ArduinoSensor saveSensorData(double humidity, double temperature, String email){
        if (humidity == 123 && temperature == 123 && email.equals("testtest")){
            logger.debug("Did not save testdata");
        } else {
            return arduinoSensorRepository.save(new ArduinoSensor(temperature, humidity, email, LocalDateTime.now()));
        }
        return null;
    }


    //only for here in this timezone
    //    @Scheduled(cron = "30 * * * * *", zone = "Europe/Paris")
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Paris")
    public void delete(){
        arduinoSensorRepository.deleteAll(arduinoSensorRepository.findAll().stream()
                .filter(arduinoData -> arduinoData.getTimeOfReading().isBefore(LocalDateTime.now().minusDays(1)))
                .collect(Collectors.toList()));
    }
}
