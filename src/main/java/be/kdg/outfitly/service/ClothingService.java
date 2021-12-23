package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;

import java.util.List;

public interface ClothingService {
    ClothingItem findById(int id);
    ClothingItem create(ClothingItem clothingItem);
    List<ClothingItem> read();
    void delete(int id, User user);
    void putInWash(ClothingItem toPutInWash);
}
