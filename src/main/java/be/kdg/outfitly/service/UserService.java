package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;

import java.util.List;

public interface UserService {

    String getUsername(User user);
    User findBytId(int id);
    User create(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode, List<ClothingItem> clothes);
    User findByEmail(String email);
    List<User> read();
    void update(User updatedUser);
}
