package be.kdg.outfitly.domain;

import java.util.List;

public class User extends Entity {
    //login details
    private String email;
    private String password;

    private String name;
    private List<ClothingItem> clothes;

    public User(String email, String password, String name, List<ClothingItem> clothes) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.clothes = clothes;
    }

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
