package be.kdg.outfitly.domain;

import java.util.List;

public class User extends Entity {

    // Variables

    private String email;
    private String password;

    private String firstName;
    private String surname;
    private String location;
    private List<ClothingItem> clothes;

    // Constructor


    public User() {
    }

    public User(String email, String password, String firstName, String surname, String location, List<ClothingItem> clothes) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.clothes = clothes;
        this.location = location;
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

    public String getSurname() {
        return surname;
    }

    //function to get first name and surname together
    public String getName(){
        return firstName + " " + surname;
    }

    public List<ClothingItem> getClothes() {
        return clothes;
    }

    public String getLocation() {
        return location;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setClothes(List<ClothingItem> clothes) {
        this.clothes = clothes;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
