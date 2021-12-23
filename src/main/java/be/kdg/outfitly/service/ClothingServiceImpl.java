package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.util.ClothingPictureTooLargeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ClothingServiceImpl implements ClothingService {
    private final Logger logger = LoggerFactory.getLogger(ClothingServiceImpl.class);

    private final ClothingRepository clothingRepository;
    private final UserService userService;

    public ClothingServiceImpl(ClothingRepository clothingRepository, UserService userService) {
        this.clothingRepository = clothingRepository;
        this.userService = userService;
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
    @Transactional
    public void delete(int id, User user) {
        User userFromRepo = userService.findById(user.getId());
        List<ClothingItem> clothes = new ArrayList<>(userFromRepo.getClothes());

        clothes.remove(clothingRepository.findById(id).orElseThrow());
        userFromRepo.setClothes(clothes);

        userService.update(userFromRepo);
        clothingRepository.delete(clothingRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public void putInWash(ClothingItem clothingItem) {
        ClothingItem toBeUpdated = clothingRepository.getById(clothingItem.getId());
        toBeUpdated.setWashCycle(true);
        clothingRepository.save(toBeUpdated);
    }

    //only for here in this timezone
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Paris")
    public void resetClothesWashCycle(){
        Calendar calendar = Calendar.getInstance();
        List<ClothingItem> clothes = clothingRepository.findAll();
        List<User> users = userService.read();

//        for(User user : users){
//            if(!user.getWashingResetDay().equals(LocalDateTime.now())){
//                continue;
//            } else {
//
//            }
//        }

        users.stream()
                .forEach(
                        user ->{
                            if(!user.getWashingResetDay().equals(LocalDateTime.now().getDayOfWeek())){
                                logger.debug(LocalDateTime.now().getDayOfWeek().toString());
                                return;
                            } else {
                                user.getClothes().forEach(clothingItem -> {
                                    clothingItem.setWashCycle(false);
                                    userService.update(user);
                                };
                            }
                        }
                );


    }
}
