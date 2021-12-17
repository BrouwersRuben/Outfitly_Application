package be.kdg.outfitly.util;

import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.exceptions.EmailExistsException;

import java.util.List;

/**
 * <p>
 *  Checks if the email that is entered by the user already exist in the database
 *  You must give the method an email to check against the database and a list of all current users to check it against.
 * </p>
 * Warning: this method might cause issues if there are user added at the exact same time.
 * @param   email   a string that is the email to be checked
 * @param   userList    The list of user against which you need to check the email
 *
 */
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
