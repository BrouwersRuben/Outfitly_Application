package be.kdg.outfitly.domain;

import java.util.List;

public class User extends Entity {
    //login details
    protected String email;
    protected String password;

    protected String name;
    protected List<ClothingItem> clothes;

    public User(String email, String password, String name, List<ClothingItem> clothes) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.clothes = clothes;
    }
}
