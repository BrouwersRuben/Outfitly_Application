package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import be.kdg.outfitly.repository.EntityRepository;
import be.kdg.outfitly.repository.ListRepository;
import be.kdg.outfitly.repository.MainUserListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.List;

public class SeedData implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(SeedData.class);
    private ArduinoSensorRepository arduinoSensorRepository;
//    private EntityRepository entityRepository;
//    private ListRepository listRepository;
//    private MainUserListRepository mainUserListRepository;
//    private ClothingItem clothingItem;

    public SeedData(ArduinoSensorRepository arduinoSensorRepository/*, EntityRepository entityRepository, ListRepository listRepository, MainUserListRepository mainUserListRepository, ClothingItem clothingItem*/) {
        this.arduinoSensorRepository = arduinoSensorRepository;
//        this.entityRepository = entityRepository;
//        this.listRepository = listRepository;
//        this.mainUserListRepository = mainUserListRepository;
//        this.clothingItem = clothingItem;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("testUser1@gmail.com","test123","John",
                List.of(new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD),
                        new ClothingItem("Hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD),
                        new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL),
                        new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM),
                        new ClothingItem("Suit", ClothingItem.Material.WOOL, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL)));
        User user2 = new User("testUser2@gmail.com","test123","Bob",
                List.of(new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD),
                        new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD),
                        new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL),
                        new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM),
                        new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL)));
        ArduinoSensor arduinoSensor = new ArduinoSensor(10, 50, LocalDateTime.of(2021, 10, 29, 12, 30, 30));

        arduinoSensorRepository.create(arduinoSensor);

        //TODO: add mainUsers, but these are users....
        // Slave and master accounts may be a bit easier to understand
//        mainUserListRepository.create(user1);
//        mainUserListRepository.create(user2);
    }
}
