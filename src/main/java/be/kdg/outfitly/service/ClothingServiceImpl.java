package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.repository.ClothingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClothingServiceImpl implements ClothingService {
    private final Logger logger = LoggerFactory.getLogger(ClothingServiceImpl.class);
    private final ClothingRepository clothingRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    @Override
    public ClothingItem findById(int id) {
        return clothingRepository.findById(id);
    }

    @Override
    public ClothingItem create(ClothingItem clothingItem) {
        return clothingRepository.create(clothingItem);
    }

    @Override
    public List<ClothingItem> read() {
        return clothingRepository.read();
    }
}
