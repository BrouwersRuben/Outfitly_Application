package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;

import java.util.List;
import java.util.Map;

public interface OutfitSelectorService {
    Map<ClothingItem.Type, List<ClothingItem>> getSuitableClothesGroupedByType(User user, ClothingItem.Occasion occasion);
    String getAiDecision();
}
