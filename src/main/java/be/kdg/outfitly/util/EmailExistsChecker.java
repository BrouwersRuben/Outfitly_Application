package be.kdg.outfitly.util;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.exceptions.EmailExistsException;

import java.util.List;

//This will check upo registration if the email is already registered in the database, if so, it will throw an error
public class EmailExistsChecker {
    public static void checkEmail(String email, List<User> userList){
        // boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()));
        for(User user: userList)     {
            if(user.getEmail().equals(email)){
                throw new EmailExistsException("This email already exists", email);
            }
        }
    }
}
