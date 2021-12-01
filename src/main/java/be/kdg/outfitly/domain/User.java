package be.kdg.outfitly.domain;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {

    // Variables

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    //String --> 047/..
    private String phoneNumber;
    private String country;
    private String city;
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String zipcode;
    private List<ClothingItem> clothes;

    // Constructor

    public User() {
    }

    //constructor for everything
    public User(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode) {
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
    public User(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, String streetNumber, String zipcode) {
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

    public String getStreetNumber() {
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

    //function to get first name and surname together
    public String getName(){
        return firstName + " " + lastName;
    }

    //setters

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

    public void setClothingItem(ClothingItem clothingItem) {
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

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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
