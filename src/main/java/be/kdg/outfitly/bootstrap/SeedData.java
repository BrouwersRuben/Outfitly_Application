package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.*;
import be.kdg.outfitly.repository.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("JavaCollections")
public class SeedData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SeedData.class);
    private final ArduinoSensorRepository arduinoSensorRepository;
    private final UserRepository userRepository;
    private final ClothingRepository clothingRepository;
    private final WeatherForecastRepository weatherForecastRepository;
    private final DailyWeatherForecastRepository dailyWeatherForecastRepository;
    private JSONObject weatherAPIData;
//    private final String arduinoAPI = "http://192.168.184.187/data";

//    JSONObject arduinoAPIData;
//    private EntityRepository entityRepository;
//    private ListRepository listRepository;
//    private MainUserListRepository mainUserListRepository;
//    private ClothingItem clothingItem;

    public SeedData(ArduinoSensorRepository arduinoSensorRepository, UserRepository userRepository, ClothingRepository clothingRepository, WeatherForecastRepository weatherForecastRepository, DailyWeatherForecastRepository dailyWeatherForecastRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
        this.userRepository = userRepository;
        this.clothingRepository = clothingRepository;
        this.weatherForecastRepository = weatherForecastRepository;
        this.dailyWeatherForecastRepository = dailyWeatherForecastRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Seeding the repositories");
        //TODO: Make this so that the location changes per user it loads.
//        arduinoAPIData = retrieveAPIData(logger, arduinoAPI);

        //Test users with some clothing items
//        User user1 = new User("testUser1@gmail.com","test123","John", "Doe", "0475441658", "Belgium", "Antwerp", "Nationale Straat", 85, "2000");
        User user1 = new User("testUser1@gmail.com", "test123", "John", "Doe", "0475441658", "Belgium", "BE", "Antwerp", "Nationale Straat", "5", "200B", "2000");
//        user1.setCountryCode("BE");
        ClothingItem clothingItem1 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.MILD, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem2 = new ClothingItem("Hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE);
        ClothingItem clothingItem3 = new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE);
        ClothingItem clothingItem4 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem5 = new ClothingItem("Sport shoes", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);
        ClothingItem clothingItem6 = new ClothingItem("Leather dress shoes", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);
        ClothingItem clothingItem7 = new ClothingItem("White dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHIRT_LIKE);
        ClothingItem clothingItem8 = new ClothingItem("Blue blazer", ClothingItem.Material.WOOL, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem9 = new ClothingItem("Short-sleeve dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.MILD, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem10 = new ClothingItem("Black hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE);
        ClothingItem clothingItem11 = new ClothingItem("Green bomber jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem12 = new ClothingItem("White polo shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem13 = new ClothingItem("Black pants", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE);
        ClothingItem clothingItem14 = new ClothingItem("Black leather sneakers", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);

        clothingRepository.create(clothingItem1);
        clothingRepository.create(clothingItem2);
        clothingRepository.create(clothingItem3);
        clothingRepository.create(clothingItem4);
        clothingRepository.create(clothingItem5);
        clothingRepository.create(clothingItem6);
        clothingRepository.create(clothingItem7);
        clothingRepository.create(clothingItem8);
        clothingRepository.create(clothingItem9);
        clothingRepository.create(clothingItem10);
        clothingRepository.create(clothingItem11);
        clothingRepository.create(clothingItem12);
        clothingRepository.create(clothingItem13);
        clothingRepository.create(clothingItem14);

        final List<ClothingItem> clothingItemsUser1 = List.of(clothingItem1, clothingItem2, clothingItem3, clothingItem4, clothingItem5, clothingItem6, clothingItem7, clothingItem8, clothingItem9, clothingItem10, clothingItem11, clothingItem12, clothingItem13, clothingItem14);
        user1.setClothes(clothingItemsUser1);

        User user2 = new User("testUser2@gmail.com", "test123", "Bob", "Shannon", "0458564572", "Spain", "ES", "Barcelona", "La Rambla", "15", "08001");
//                List.of(new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD),
//                        new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD),
//                        new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL),
//                        new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM),
//                        new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL)));


        ClothingItem clothingItem15 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.MILD);
        ClothingItem clothingItem16 = new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD);
        ClothingItem clothingItem17 = new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL);
        ClothingItem clothingItem18 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.WARM);
        ClothingItem clothingItem19 = new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL);

        final List<ClothingItem> clothingItemsUser2 = List.of(clothingItem15, clothingItem16, clothingItem17, clothingItem18, clothingItem19);
        user2.setClothes(clothingItemsUser2);

        userRepository.create(user1);
        userRepository.create(user2);

        // Arduino API
        ArduinoSensor arduinoSensor = new ArduinoSensor(10, 50, LocalDateTime.of(2021, 10, 29, 12, 30, 30));
//        ArduinoSensor arduinoSensor = new ArduinoSensor(
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Temperature"))),
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Humidity"))),
//                LocalDateTime.parse(String.valueOf(arduinoAPIData.get("DateTime"))))                ;

        arduinoSensorRepository.create(arduinoSensor);

        WeatherForecast forecast = WeatherForecast.currentForecastForCountryCity(user1.getCountryCode(), user1.getCity());
        if (forecast != null) weatherForecastRepository.create(forecast);
        DailyWeatherForecast forecast2 = DailyWeatherForecast.dailyForecast(user1.getCountryCode(), user1.getCity(), forecast.getLatitude(), forecast.getLongitude());
        if (forecast2 != null) dailyWeatherForecastRepository.create(forecast2);

        // Slave and master accounts may be a bit easier to understand
    }


}
