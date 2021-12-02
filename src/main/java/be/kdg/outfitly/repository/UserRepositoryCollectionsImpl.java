package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JavaCollections")
public class UserRepositoryCollectionsImpl extends ListRepository<User> implements UserRepository{
    private final Logger logger = LoggerFactory.getLogger(UserRepositoryCollectionsImpl.class);

    public UserRepositoryCollectionsImpl() {
        logger.debug("Creating user repository (Collections)");
    }
}
