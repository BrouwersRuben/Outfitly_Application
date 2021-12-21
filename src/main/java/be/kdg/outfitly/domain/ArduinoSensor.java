package be.kdg.outfitly.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name = "arduino_forecast")
public class ArduinoSensor extends Entity {

    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature", nullable = false)
    private double sensorTemperature;

    @Column(name = "humidity", nullable = false)
    private double sensorHumidity;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "forecast_timestamp", nullable = false)
    private LocalDateTime timeOfReading;

    // Constructor
    protected ArduinoSensor() {
    }

    public ArduinoSensor(double sensorTemperature, double sensorHumidity, String email, LocalDateTime timeOfReading) {
        this.sensorTemperature = sensorTemperature;
        this.sensorHumidity = sensorHumidity;
        this.email = email;
        this.timeOfReading = timeOfReading;
    }

    // Getters

    @Override
    public int getId() {
        return id;
    }

    public double getSensorTemperature() {
        return sensorTemperature;
    }

    public double getSensorHumidity() {
        return sensorHumidity;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getTimeOfReading() {
        return timeOfReading;
    }

    // Setters

    public void setSensorTemperature(double sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    public void setSensorHumidity(double sensorHumidity) {
        this.sensorHumidity = sensorHumidity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimeOfReading(LocalDateTime timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    @Override
    public String toString() {
        return "ArduinoSensor{" + "sensorTemperature=" + sensorTemperature + ", sensorHumidity=" + sensorHumidity + ", timeOfReading=" + timeOfReading + '}';
    }
}
