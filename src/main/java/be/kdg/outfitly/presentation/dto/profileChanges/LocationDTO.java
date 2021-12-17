package be.kdg.outfitly.presentation.dto.profileChanges;

import be.kdg.outfitly.presentation.dto.Locatable;
import be.kdg.outfitly.presentation.dto.LocationValidation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@LocationValidation
public class LocationDTO implements Locatable {

    @NotEmpty(message = "Please fill in your country.")
    @Size(max = 56)
    private String country;
    @NotEmpty(message = "Please select your country code.")
    @Size(max = 2, min = 2)
    private String countryCode;
    @NotEmpty(message ="Please fill in your city.")
    @Size(max = 85, message = "City name cannot be larger than 85 characters.")
    private String city;
    @NotEmpty(message = "Please fill in a street name.")
    @Size(max = 50, message =  "Street name cannot be larger than 50 characters.")
    private String streetName;
    @NotEmpty(message = "Please fill in a street number.")
    @Size(max = 10, message = "Street number cannot be larger than 10 characters.")
    private String streetNumber;
    @Size(max = 10, message = "Apartment number cannot be larger than 10 characters.")
    private String apartmentNumber;
    @NotEmpty
    @Size(max = 10, message = "Zipcode cannot be larger than 10 characters")
    private String zipcode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
