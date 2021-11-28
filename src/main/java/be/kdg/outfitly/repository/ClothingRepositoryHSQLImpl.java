package be.kdg.outfitly.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public class ClothingRepositoryHSQLImpl {
}
