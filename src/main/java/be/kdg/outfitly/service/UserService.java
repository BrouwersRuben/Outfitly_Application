package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.User;

public interface UserService {

    String getUsername(User user);
    User findBytId(int id);

}
