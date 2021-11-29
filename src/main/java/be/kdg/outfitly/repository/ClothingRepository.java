package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ClothingItem;

import java.util.List;

public interface ClothingRepository {
    List<ClothingItem> read();
    ClothingItem findById(int id);
    ClothingItem create(ClothingItem clothingItem);
    void delete(ClothingItem clothingItem);
}
