package be.kdg.outfitly.service;


import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.UserRepository;
import be.kdg.outfitly.repository.UserRepositoryCollectionsImpl;
import be.kdg.outfitly.util.EmailExistsChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
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
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
       return userRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User create(String email, String password, String firstName, String lastName, DayOfWeek washday,String phoneNumber, String country, String countryCode, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode, List<ClothingItem> clothes) {
        User user;
        if(apartmentNumber == null){
            logger.debug("User didn't fill in a ap number");
            user = new User(email, password, firstName, lastName, washday, phoneNumber, country, countryCode, city, streetName, streetNumber, zipcode);
        }else{
            logger.debug("User filled in an ap number");
            user = new User(email, password, firstName, lastName, washday, phoneNumber, country, countryCode, city, streetName, streetNumber, apartmentNumber, zipcode);
        }
        EmailExistsChecker.checkEmail(user.getEmail(), userRepository.findAll());
        return userRepository.save(user);
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public void update(User updatedUser){
        User newUser = userRepository.getById(updatedUser.getId());
        newUser.merge(updatedUser);
        userRepository.save(newUser);
    }
}
