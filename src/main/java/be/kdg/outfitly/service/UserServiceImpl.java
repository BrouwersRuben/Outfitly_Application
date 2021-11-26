package be.kdg.outfitly.service;


import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getUsername(User user) {
        return user.getName();
    }

    @Override
    public User findBytId(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(String email, String password, String firstName, String surname, String location, List<ClothingItem> clothes) {
        User user = new User(email, password, firstName, surname, location, clothes);
        return userRepository.create(user);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }


}
