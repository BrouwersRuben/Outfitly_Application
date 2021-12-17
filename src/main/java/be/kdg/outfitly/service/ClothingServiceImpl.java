package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.util.ClothingPictureTooLargeChecker;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClothingServiceImpl implements ClothingService {
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
        ClothingPictureTooLargeChecker.checkPictureSize(clothingItem.getPhoto());

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

    @Override
    @Transactional
    public void putInWash(ClothingItem clothingItem) {
        ClothingItem toBeUpdated = clothingRepository.getById(clothingItem.getId());
        toBeUpdated.setWash_cycle(true);
        clothingRepository.save(toBeUpdated);
    }
}
