package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.repository.ArduinoSensorRepository;
import be.kdg.outfitly.repository.ClothingRepository;
import be.kdg.outfitly.repository.UserRepository;
import be.kdg.outfitly.repository.WeatherForecastRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Transactional
//@Profile("Hibernate")
public class SeedDataSQL implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SeedDataSQL.class);
    private final ArduinoSensorRepository arduinoSensorRepository;
    private final UserRepository userRepository;
    private final ClothingRepository clothingRepository;
    private final WeatherForecastRepository weatherForecastRepository;
    private JSONObject weatherAPIData;
//    private final String arduinoAPI = "http://192.168.184.187/data";

//    JSONObject arduinoAPIData;
//    private EntityRepository entityRepository;
//    private ListRepository listRepository;
//    private MainUserListRepository mainUserListRepository;
//    private ClothingItem clothingItem;


    public SeedDataSQL(ArduinoSensorRepository arduinoSensorRepository, UserRepository userRepository, ClothingRepository clothingRepository, WeatherForecastRepository weatherForecastRepository) {
        this.arduinoSensorRepository = arduinoSensorRepository;
        this.userRepository = userRepository;
        this.clothingRepository = clothingRepository;
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Seeding the repositories ");
        //TODO: Make this so that the location changes per user it loads.
//        arduinoAPIData = retrieveAPIData(logger, arduinoAPI);

        // This is some initial data for testing purposes.
        User user1 = new User("testUser1@gmail.com", "test123", "John", "Doe", DayOfWeek.WEDNESDAY, "0475441658", "Belgium", "BE","Antwerp", "Nationale Straat", "5", "200B", "2000");

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


        // Arduino API
        ArduinoSensor arduinoSensor = new ArduinoSensor(10, 50, LocalDateTime.now());
//        ArduinoSensor arduinoSensor = new ArduinoSensor(
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Temperature"))),
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Humidity"))),
//                LocalDateTime.parse(String.valueOf(arduinoAPIData.get("DateTime"))))                ;

        //TODO: Added every day automatically retrieved from db
        /*arduinoSensorRepository.create(arduinoSensor);*/

        arduinoSensor.setUser(user1);
        user1.setSensorData(List.of(arduinoSensor));

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



        logger.warn("Test user creation to see clothes: "+testUser);

        User user2 = new User("testUser2@gmail.com","test123","Bob","Shannon", DayOfWeek.WEDNESDAY,"0458564572", "Spain", "ES", "Barcelona", "La Rambla", "15", "08001");
//                List.of(new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD),
//                        new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD),
//                        new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL),
//                        new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM),
//                        new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL)));


        ClothingItem clothingItem15 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD, ClothingItem.Type.JACKET_LIKE, false);
        ClothingItem clothingItem16 = new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE, false);
        ClothingItem clothingItem17 = new ClothingItem("Blue Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE, false);
        ClothingItem clothingItem18 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE, false);
        ClothingItem clothingItem19 = new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHIRT_LIKE, false);

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

        ArduinoSensor arduinoSensor2 = new ArduinoSensor(99, 300, LocalDateTime.now());
        user2.setSensorData(List.of(arduinoSensor2));
        arduinoSensor2.setUser(user2);

        userRepository.save(user2);

        // Weather API
//        WeatherForecast forecast = new WeatherForecast(
//                LocalDateTime.of(2021, 10, 29, 12, 30, 30),
//                String.valueOf(weatherAPIData.get("name")),
//                String.valueOf(weatherAPIData.getJSONObject("sys").get("country")),
//                Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp"))),
//                Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("feels_like"))),
//                Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp_min"))),
//                Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("main").get("temp_max"))),
//                Double.parseDouble(String.valueOf(weatherAPIData.getJSONObject("wind").get("speed"))),
//                Integer.parseInt(String.valueOf(weatherAPIData.getJSONObject("main").get("humidity"))),
//                String.valueOf(weatherAPIData.getJSONArray("weather").getJSONObject(0).get("main"))
//        );

        WeatherForecast forecast = WeatherForecast.currentForecastForCountryCity("Belgium", "Antwerp");
        if (forecast != null) weatherForecastRepository.save(forecast);

        // Slave and master accounts may be a bit easier to understand
    }


}
