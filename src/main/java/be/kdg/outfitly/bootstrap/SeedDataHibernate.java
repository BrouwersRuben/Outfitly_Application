package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.ArduinoSensor;
import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.domain.WeatherForecast;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("Hibernate")
public class SeedDataHibernate implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SeedDataHibernate.class);
    private JSONObject weatherAPIData;
//    private final String arduinoAPI = "http://192.168.184.187/data";

//    JSONObject arduinoAPIData;
//    private EntityRepository entityRepository;
//    private ListRepository listRepository;
//    private MainUserListRepository mainUserListRepository;
//    private ClothingItem clothingItem;

/*    public SeedDataHibernate() {

    }*/

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Seeding the repositories (Hibernate) ");
//        arduinoAPIData = retrieveAPIData(logger, arduinoAPI);

        // This is some initial data for testing purposes.
//        User user1 = new User("testUser1@gmail.com","test123","John", "Doe", "0475441658", "Belgium", "Antwerp", "Nationale Straat", 85, "2000");
        User user1 = new User("testUser1@gmail.com", "test123", "John", "Doe", "0475441658", "Belgium", "Antwerp", "Nationale Straat", 5, "200B", "2000");

        ClothingItem clothingItem1 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem2 = new ClothingItem("Hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE);
        ClothingItem clothingItem3 = new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE);
        ClothingItem clothingItem4 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem5 = new ClothingItem("Sport shoes", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);
        ClothingItem clothingItem6 = new ClothingItem("Leather dress shoes", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);
        ClothingItem clothingItem7 = new ClothingItem("White dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHIRT_LIKE);
        ClothingItem clothingItem8 = new ClothingItem("Blue blazer", ClothingItem.Material.WOOL, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem9 = new ClothingItem("Short-sleeve dress shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.ELEGANT, ClothingItem.Weather.MILD, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem10 = new ClothingItem("Black hoodie", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.SWEATSHIRT_LIKE);
        ClothingItem clothingItem11 = new ClothingItem("Green bomber jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL, ClothingItem.Occasion.CASUAL, ClothingItem.Weather.COLD, ClothingItem.Type.JACKET_LIKE);
        ClothingItem clothingItem12 = new ClothingItem("White polo shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.WARM, ClothingItem.Type.T_SHIRT_LIKE);
        ClothingItem clothingItem13 = new ClothingItem("Black pants", ClothingItem.Material.OTHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.TROUSERS_LIKE);
        ClothingItem clothingItem14 = new ClothingItem("Black leather sneakers", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.BAD, ClothingItem.Occasion.UNIVERSAL, ClothingItem.Weather.UNIVERSAL, ClothingItem.Type.SHOES);

        // TODO: Add all clothing items to table

        final List<ClothingItem> clothingItemsUser1 = List.of(clothingItem1, clothingItem2, clothingItem3, clothingItem4, clothingItem5, clothingItem6, clothingItem7, clothingItem8, clothingItem9, clothingItem10, clothingItem11, clothingItem12, clothingItem13, clothingItem14);
        user1.setClothes(clothingItemsUser1);

        // TODO: Add user 1 to table

        User user2 = new User("testUser2@gmail.com","test123","Bob","Shannon","0458564572", "Spain", "Barcelona", "La Rambla", 15, "08001");

        ClothingItem clothingItem15 = new ClothingItem("Jacket", ClothingItem.Material.LEATHER, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.MILD);
        ClothingItem clothingItem16 = new ClothingItem("Hoodie", ClothingItem.Material.SYNTHETIC, ClothingItem.RainProofness.NORMAL,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.COLD);
        ClothingItem clothingItem17 = new ClothingItem("Jeans", ClothingItem.Material.DENIM, ClothingItem.RainProofness.GOOD,ClothingItem.Occasion.UNIVERSAL,ClothingItem.Weather.UNIVERSAL);
        ClothingItem clothingItem18 = new ClothingItem("T-Shirt", ClothingItem.Material.COTTON, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.CASUAL,ClothingItem.Weather.WARM);
        ClothingItem clothingItem19 = new ClothingItem("Suit", ClothingItem.Material.SILK, ClothingItem.RainProofness.BAD,ClothingItem.Occasion.ELEGANT,ClothingItem.Weather.UNIVERSAL);

        // TODO: Add all clothing items to table

        final List<ClothingItem> clothingItemsUser2 = List.of(clothingItem15, clothingItem16, clothingItem17, clothingItem18, clothingItem19);
        user2.setClothes(clothingItemsUser2);

        // TODO: Add user 2 to table

        // Arduino API
        ArduinoSensor arduinoSensor = new ArduinoSensor(10, 50, LocalDateTime.of(2021, 10, 29, 12, 30, 30));
//        ArduinoSensor arduinoSensor = new ArduinoSensor(
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Temperature"))),
//                Double.parseDouble(String.valueOf(arduinoAPIData.get("Humidity"))),
//                LocalDateTime.parse(String.valueOf(arduinoAPIData.get("DateTime"))))                ;

        // TODO: Add arduinoSensor to table

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
//        TODO: Add forecast to table
        if (forecast != null);

        // Slave and master accounts may be a bit easier to understand
    }


}
