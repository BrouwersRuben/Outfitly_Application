package be.kdg.outfitly.domain;

import java.util.List;

public class MainUser extends Entity{

    // Variables

    private List<User> subUsers;

    // Getters

    public List<User> getSubUsers() {
        return subUsers;
    }

    // Setters

    public void setSubUsers(List<User> subUsers) {
        this.subUsers = subUsers;
    }
}
