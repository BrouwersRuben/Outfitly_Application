package be.kdg.outfitly.domain;

import java.time.LocalDateTime;

public class ArduinoSensor {

    // Variables

    private int sensorTemperature;
    private int sensorHumidity;
    private LocalDateTime timeOfReading;

    // Constructor

    public ArduinoSensor(int sensorTemperature, int sensorHumidity, LocalDateTime timeOfReading) {
        this.sensorTemperature = sensorTemperature;
        this.sensorHumidity = sensorHumidity;
        this.timeOfReading = timeOfReading;
    }

    // Getters

    public int getSensorTemperature() {
        return sensorTemperature;
    }

    public int getSensorHumidity() {
        return sensorHumidity;
    }

    public LocalDateTime getTimeOfReading() {
        return timeOfReading;
    }

    // Setters

    public void setSensorTemperature(int sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    public void setSensorHumidity(int sensorHumidity) {
        this.sensorHumidity = sensorHumidity;
    }

    public void setTimeOfReading(LocalDateTime timeOfReading) {
        this.timeOfReading = timeOfReading;
    }
}
