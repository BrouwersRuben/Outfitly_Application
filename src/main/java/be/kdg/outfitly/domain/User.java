package be.kdg.outfitly.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "costumers")
public class User extends Entity {

    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 50)
    //String --> 047/..
    private String phoneNumber;

    @Column(name = "country", nullable = false, length = 56)
    private String country;

    @Column(name = "city", nullable = false, length = 85)
    private String city;

    @Column(name = "street_name", nullable = false, length = 50)
    private String streetName;

    @Column(name = "street_number", nullable = false, length = 10)
    private int streetNumber;

    @Column(name = "appartment_number", length = 10)
    private String apartmentNumber;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipcode;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClothingItem> clothes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ArduinoSensor> sensorData;

    // Constructor
    // TODO: make this protected MULTI USER
    public User() {
    }

    //constructor for everything
    public User(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, int streetNumber, String apartmentNumber, String zipcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipcode = zipcode;
        this.clothes = new ArrayList<>();
    }

    //Constructor for without apartmentNumber
    public User(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, int streetNumber, String zipcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
        this.clothes = new ArrayList<>();
    }

    public void addClothing(ClothingItem clothingItem){
        clothes.add(clothingItem);
    }

    // Getters

    @Override
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<ClothingItem> getClothes() {
        return clothes;
    }

    public List<ArduinoSensor> getSensorData() {
        return sensorData;
    }

    //function to get first name and surname together
    public String getName(){
        return firstName + " " + lastName;
    }

    //setters

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setClothes(List<ClothingItem> clothes) {
        this.clothes = clothes;
    }

    public void addClothingItem(ClothingItem clothingItem) {
        this.clothes.add(clothingItem);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setSensorData(List<ArduinoSensor> sensorData) {
        this.sensorData = sensorData;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", clothes=" + clothes +
                '}';
    }
}
