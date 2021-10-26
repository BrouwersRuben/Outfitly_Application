package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserRepository extends ListRepository<User> {
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        logger.debug("Creating user repository...");
    }

    private void addUser(User user){

    }
}
