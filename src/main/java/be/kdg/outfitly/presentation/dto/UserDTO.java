package be.kdg.outfitly.presentation.dto;

import be.kdg.outfitly.util.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;

@LocationValidation
public class UserDTO implements Locatable {

    @Email
    @NotEmpty(message = "The email should be given.")
    private String email;
    @ValidPassword
    @NotEmpty()
    private String password;
    @NotEmpty(message = "Please fill in your first name.")
    @Size(max = 50, message = "First name cannot be larger than 50 characters.")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Please fill in your last name.")
    @Size(max = 50, message = "Last name cannot be larger than 50 characters.")
    private String lastName;
    @NotEmpty(message = "Please fill in your phone number.")
    @Size(min = 10, max = 50, message = "Phone number must be between 10 to 50 characters long.")
    private String phoneNumber;
    @NotEmpty(message = "Please fill in your country.")
    @Size(min = 2, max = 2)
    private String countryCode;
    @NotEmpty(message = "Please fill in your city.")
    @Size(max = 85, message = "City name cannot be larger than 85 characters.")
    private String city;
    @NotEmpty(message = "Please fill in a street name.")
    @Size(max = 50, message = "Street name cannot be larger than 50 characters.")
    private String streetName;
    @NotEmpty(message = "Please fill in a street number.")
    @Size(max = 10, message = "Street number cannot be larger than 10 characters.")
    private String streetNumber;
    @Size(max = 10, message = "Apartment number cannot be larger than 10 characters.")
    private String apartmentNumber;
    @NotEmpty
    @Size(max = 10, message = "Zipcode cannot be larger than 10 characters")
    private String zipcode;
    @NotNull(message = "The washingday should be given")
    private DayOfWeek washDay;

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public DayOfWeek getWashDay() {
        return washDay;
    }

    public void setWashDay(DayOfWeek washDay) {
        this.washDay = washDay;
    }
}
