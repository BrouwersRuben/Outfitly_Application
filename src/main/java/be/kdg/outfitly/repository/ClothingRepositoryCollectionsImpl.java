package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ClothingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JavaCollections")
public class ClothingRepositoryCollectionsImpl extends ListRepository<ClothingItem> implements ClothingRepository {
    private final Logger logger = LoggerFactory.getLogger(ClothingRepositoryCollectionsImpl.class);

    public ClothingRepositoryCollectionsImpl() {
        logger.debug("Creating Clothing repository (Collections)");
    }
}
