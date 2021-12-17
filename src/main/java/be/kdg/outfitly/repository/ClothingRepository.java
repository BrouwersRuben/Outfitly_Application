package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<ClothingItem, Integer> {
}
