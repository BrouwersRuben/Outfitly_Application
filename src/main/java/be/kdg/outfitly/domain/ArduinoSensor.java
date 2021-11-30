package be.kdg.outfitly.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name = "arduino_forecast")
public class ArduinoSensor extends Entity{

    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature", nullable = false)
    private double sensorTemperature;

    @Column(name = "humidity", nullable = false)
    private double sensorHumidity;

    @Column(name = "forecast_timestamp", nullable = false)
    private LocalDateTime timeOfReading;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    // Constructor
    protected ArduinoSensor(){}

    public ArduinoSensor(double sensorTemperature, double sensorHumidity, LocalDateTime timeOfReading) {
        this.sensorTemperature = sensorTemperature;
        this.sensorHumidity = sensorHumidity;
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

    public LocalDateTime getTimeOfReading() {
        return timeOfReading;
    }

    public User getUser() {
        return user;
    }

    // Setters

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setSensorTemperature(double sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    public void setSensorHumidity(double sensorHumidity) {
        this.sensorHumidity = sensorHumidity;
    }

    public void setTimeOfReading(LocalDateTime timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    public void setUser(User user) {
        this.user = user;
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
