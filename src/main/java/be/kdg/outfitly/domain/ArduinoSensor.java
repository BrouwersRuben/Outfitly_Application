package be.kdg.outfitly.domain;

import java.time.LocalDateTime;

public class ArduinoSensor extends Entity{

    // Variables

    private double sensorTemperature;
    private double sensorHumidity;
    private LocalDateTime timeOfReading;

    // Constructor

    public ArduinoSensor(double sensorTemperature, double sensorHumidity, LocalDateTime timeOfReading) {
        this.sensorTemperature = sensorTemperature;
        this.sensorHumidity = sensorHumidity;
        this.timeOfReading = timeOfReading;
    }

    // Getters

    public double getSensorTemperature() {
        return sensorTemperature;
    }

    public double getSensorHumidity() {
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

    @Override
    public String toString() {
        return "ArduinoSensor{" +
                "sensorTemperature=" + sensorTemperature +
                ", sensorHumidity=" + sensorHumidity +
                ", timeOfReading=" + timeOfReading +
                '}';
    }
}
