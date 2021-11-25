package be.kdg.outfitly.domain;

import java.util.List;

public class User extends Entity {

    // Variables

    private String email;
    private String password;

    private String name;
    private String location;
    private List<ClothingItem> clothes;

    // Constructor


    public User() {
    }

    public User(String email, String password, String name, String location, List<ClothingItem> clothes) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setClothes(List<ClothingItem> clothes) {
        this.clothes = clothes;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
