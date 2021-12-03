package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.User;

import java.util.List;

public interface UserRepository {
    User create (User user);
    List<User> read();
    User findById(int id);
    void update (User user);
}
