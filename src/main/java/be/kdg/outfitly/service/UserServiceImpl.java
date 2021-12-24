package be.kdg.outfitly.service;


import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.UserRepository;
import be.kdg.outfitly.util.EmailExistsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User create(String email, String password, String firstName, String lastName, DayOfWeek washday, String phoneNumber, String countryCode, String city, String streetName, String streetNumber, String apartmentNumber, String zipcode, List<ClothingItem> clothes) {
        User user = new User(email, password, firstName, lastName, washday, phoneNumber, countryCode, city, streetName, streetNumber, apartmentNumber == null ? "" : apartmentNumber, zipcode);
        EmailExistsChecker.checkEmail(user.getEmail(), userRepository.findAll());
        return userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public void update(User updatedUser) {
        User newUser = userRepository.getById(updatedUser.getId());
        newUser.merge(updatedUser);
        userRepository.save(newUser);
    }

    @Override
    public void addClothingItem(int usersID, ClothingItem item) {
       User user = userRepository.getById(usersID);
       item.setUser(user);
       user.addClothingItem(item);
       userRepository.save(user);
    }
}
