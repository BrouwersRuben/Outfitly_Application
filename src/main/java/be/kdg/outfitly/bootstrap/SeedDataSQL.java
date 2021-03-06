package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Transactional
public class SeedDataSQL implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SeedDataSQL.class);
    private final UserRepository userRepository;
    private final ClothingRepository clothingRepository;


    public SeedDataSQL(UserRepository userRepository, ClothingRepository clothingRepository) {
        this.userRepository = userRepository;
        this.clothingRepository = clothingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Seeding the repositories ");

        // This is some initial data for testing purposes.
        User user1 = new User("testUser1@gmail.com", "test123", "John", "Doe", DayOfWeek.WEDNESDAY, "0475441658", "BE", "Antwerp", "Nationale Straat", "5", "200B", "2000");

        ClothingItem clothingItem1 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD, ClothingItem.Type.JACKET_LIKE, false);
        ClothingItem clothingItem2 = new ClothingItem("Hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE, false);
        ClothingItem clothingItem3 = new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE, false);
        ClothingItem clothingItem4 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE, false);
        ClothingItem clothingItem5 = new ClothingItem("Sport shoes", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES, false);
        ClothingItem clothingItem6 = new ClothingItem("Leather dress shoes", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES, false);
        ClothingItem clothingItem7 = new ClothingItem("White dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHIRT_LIKE, false);
        ClothingItem clothingItem8 = new ClothingItem("Blue blazer", ClothingItem.Material.WOOL, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.JACKET_LIKE, false);
        ClothingItem clothingItem9 = new ClothingItem("Short-sleeve dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.MILD, ClothingItem.Type.T_SHIRT_LIKE, false);
        ClothingItem clothingItem10 = new ClothingItem("Black hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE, false);
        ClothingItem clothingItem11 = new ClothingItem("Green bomber jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.JACKET_LIKE, false);
        ClothingItem clothingItem12 = new ClothingItem("White polo shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE, false);
        ClothingItem clothingItem13 = new ClothingItem("Black pants", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE, false);
        ClothingItem clothingItem14 = new ClothingItem("Black leather sneakers", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES, false);

        final List<ClothingItem> clothingItemsUser1 = List.of(clothingItem1, clothingItem2, clothingItem3, clothingItem4, clothingItem5, clothingItem6, clothingItem7, clothingItem8, clothingItem9, clothingItem10, clothingItem11, clothingItem12, clothingItem13, clothingItem14);
        clothingItemsUser1.forEach(clothingItem -> {
            user1.addClothingItem(clothingItem);
            clothingItem.setUser(user1);
        });


        User testUser = userRepository.save(user1);


        clothingRepository.save(clothingItem1);
        clothingRepository.save(clothingItem2);
        clothingRepository.save(clothingItem3);
        clothingRepository.save(clothingItem4);
        clothingRepository.save(clothingItem5);
        clothingRepository.save(clothingItem6);
        clothingRepository.save(clothingItem7);
        clothingRepository.save(clothingItem8);
        clothingRepository.save(clothingItem9);
        clothingRepository.save(clothingItem10);
        clothingRepository.save(clothingItem11);
        clothingRepository.save(clothingItem12);
        clothingRepository.save(clothingItem13);
        clothingRepository.save(clothingItem14);


        logger.warn("Test user creation to see clothes: " + testUser);

        User user2 = new User("testUser2@gmail.com", "test123", "Bob", "Shannon", DayOfWeek.WEDNESDAY, "0458564572", "ES", "Barcelona", "La Rambla", "15", "", "08001");

        ClothingItem clothingItem15 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.MILD, ClothingItem.Type.JACKET_LIKE, false);
        ClothingItem clothingItem16 = new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE, false);
        ClothingItem clothingItem17 = new ClothingItem("Blue Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE, false);
        ClothingItem clothingItem18 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE, false);
        ClothingItem clothingItem19 = new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHIRT_LIKE, false);

        clothingRepository.save(clothingItem15);
        clothingRepository.save(clothingItem16);
        clothingRepository.save(clothingItem17);
        clothingRepository.save(clothingItem18);
        clothingRepository.save(clothingItem19);

        final List<ClothingItem> clothingItemsUser2 = List.of(clothingItem15, clothingItem16, clothingItem17, clothingItem18, clothingItem19);
        clothingItemsUser2.forEach(clothingItem -> {
            user2.addClothingItem(clothingItem);
            clothingItem.setUser(user2);
        });

        userRepository.save(user2);
    }
}
