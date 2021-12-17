package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.util.ClothingPictureTooLargeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingServiceImpl implements ClothingService {
    private final Logger logger = LoggerFactory.getLogger(ClothingServiceImpl.class);
    private final ClothingRepository clothingRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    @Override
    public ClothingItem findById(int id) {
        return clothingRepository.findById(id).orElseThrow();
    }

    @Override
    public ClothingItem create(ClothingItem clothingItem) {
        ClothingPictureTooLargeChecker.checkPictureSize(clothingItem.getPhoto(), clothingItem.getPhotoMIMEType(), 10_000);
        return clothingRepository.save(clothingItem);
    }

    @Override
    public List<ClothingItem> read() {
        return clothingRepository.findAll();
    }

    @Override
    public void delete(int id) {
        ClothingItem clothingItemToRemove = clothingRepository.findById(id).orElseThrow();
        clothingRepository.delete(clothingItemToRemove);
    }
}
