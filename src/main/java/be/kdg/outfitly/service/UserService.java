package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;

import java.util.List;

public interface UserService {

    String getUsername(User user);
    User findBytId(int id);
    User create(String email, String password, String name, String location, List<ClothingItem> clothes);
    List<User> read();

}
