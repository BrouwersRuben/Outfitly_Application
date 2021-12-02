package be.kdg.outfitly.service;


import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getUsername(User user) {
        return user.getName();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(String email, String password, String firstName, String lastName, String phoneNumber, String country, String city, String streetName, int streetNumber, String apartmentNumber, String zipcode, List<ClothingItem> clothes) {
        User user;
        if(apartmentNumber == null){
            logger.debug("User didn't fill in a ap number");
            user = new User(email, password, firstName, lastName, phoneNumber, country, city, streetName, streetNumber, zipcode);
        }else{
            logger.debug("User filled in an ap number");
            user = new User(email, password, firstName, lastName, phoneNumber, country, city, streetName, streetNumber, apartmentNumber,zipcode);
        }
        return userRepository.create(user);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public void update(User updatedUser){
        userRepository.update(updatedUser);
    }
}
