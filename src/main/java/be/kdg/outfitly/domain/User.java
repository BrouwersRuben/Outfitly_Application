package be.kdg.outfitly.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@DynamicUpdate
@Table(name = "users")
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


    @Column(name = "countryCode", nullable = false, length = 2)
    private String countryCode;

    @Column(name = "city", nullable = false, length = 85)
    private String city;

    @Column(name = "street_name", nullable = false, length = 50)
    private String streetName;

    @Column(name = "street_number", nullable = false, length = 10)
    private String streetNumber;

    @Column(name = "appartment_number", length = 10)
    private String apartmentNumber;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipcode;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<ClothingItem> clothes;

    @Column(name = "washing_reset_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek washingResetDay;

    // Constructor
    // TODO: make this protected MULTI USER
    public User() {
    }

    //constructor for everything
    public User(String email, String password, String firstName, String lastName, DayOfWeek washingResetDay, String phoneNumber, String countryCode, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.washingResetDay = washingResetDay;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipcode = zipcode;
        this.clothes = new ArrayList<>();
    }

    //Constructor for without apartmentNumber
    public User(String email, String password, String firstName, String lastName, DayOfWeek washingResetDay, String phoneNumber, String countryCode, String city, String streetName, String streetNumber, String zipcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.washingResetDay = washingResetDay;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
        this.clothes = new ArrayList<>();
    }

    public void addClothing(ClothingItem clothingItem) {
        clothes.add(clothingItem);
    }

    public User merge(User other) {
        setEmail(other.getEmail());
        setPassword(other.getPassword());
        setFirstName(other.getFirstName());
        setLastName(other.getLastName());
        setPhoneNumber(other.getPhoneNumber());
//        setCountry(other.getCountry());
        setCountryCode(other.getCountryCode());
        setCity(other.getCity());
        setStreetName(other.getStreetName());
        setStreetNumber(other.getStreetNumber());
        setApartmentNumber(other.getApartmentNumber());
        setZipcode(other.getZipcode());
        return other;
    }

    // Getters
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DayOfWeek getWashingResetDay() {
        return washingResetDay;
    }

    public void setWashingResetDay(DayOfWeek washingResetDay) {
        this.washingResetDay = washingResetDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getCity() {
        return city;
    }

    //setters

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<ClothingItem> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothingItem> clothes) {
        this.clothes = clothes;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String country) {
        this.countryCode = country;
    }

    //function to get first name and surname together
    public String getName() {
        return firstName + " " + lastName;
    }

    public void addClothingItem(ClothingItem clothingItem) {
        this.clothes.add(clothingItem);
    }

    @Override
    public String toString() {
        return "User{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phoneNumber='" + phoneNumber + '\''  + ", countryCODE='" + countryCode + '\'' + ", city='" + city + '\'' + ", streetName='" + streetName + '\'' + ", streetNumber=" + streetNumber + ", apartmentNumber='" + apartmentNumber + '\'' + ", zipcode='" + zipcode + '\'' + ", clothes=" + clothes + '}';
    }
}
