package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;

import java.time.DayOfWeek;
import java.util.List;

public interface UserService {
    User findById(int id);
    User create(String email, String password, String firstName, String lastName, DayOfWeek washday, String phoneNumber, String countryCode, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode, List<ClothingItem> clothes);
    User create(User user);
    User findByEmail(String email);
    List<User> read();
    void update(User updatedUser);
    void addClothingItem(int usersID, ClothingItem item);
}
