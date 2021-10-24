package be.kdg.outfitly.domain;

import java.util.List;

public class User extends Entity {

    // Variables

    private final String email;
    private final String password;

    private final String name;
    private final List<ClothingItem> clothes;

    // Constructor

    public User(String email, String password, String name, List<ClothingItem> clothes) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.clothes = clothes;
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
}
