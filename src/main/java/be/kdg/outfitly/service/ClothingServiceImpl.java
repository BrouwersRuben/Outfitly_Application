package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.util.ClothingPictureTooLargeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.debug("Create clothing item - before exception.");
        ClothingPictureTooLargeChecker.checkPictureSize(clothingItem.getPhoto(), clothingItem.getPhotoMIMEType(), 10_000);
        logger.debug("Create clothing item - there was no exception.");

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
