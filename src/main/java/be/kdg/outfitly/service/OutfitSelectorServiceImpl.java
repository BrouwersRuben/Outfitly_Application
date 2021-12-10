package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.OutfitSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutfitSelectorServiceImpl implements OutfitSelectorService {
    //TODO: What do you guys think of putting most of the code for the AI here?
    //Can this service call other service classes?

    private final Logger logger = LoggerFactory.getLogger(OutfitSelectorServiceImpl.class);

    private final UserService userService;
    private final ClothingService clothingService;
    private final ArduinoSensorService arduinoSensorService;
    private final WeatherForecastService weatherForecastService;
    private ClothingItem.Occasion occasion;

    public OutfitSelectorServiceImpl(UserService userService, ClothingService clothingService, ArduinoSensorService arduinoSensorService, WeatherForecastService weatherForecastService) {
        this.userService = userService;
        this.clothingService = clothingService;
        this.arduinoSensorService = arduinoSensorService;
        this.weatherForecastService = weatherForecastService;
    }

    private double rightTemperature(double apiTemp, double arduinoTemp) {
        final double acceptableRange = 3; //the api can be 3° over or under the captured temperature of the arduino to still be counted as correct.
        if (apiTemp - acceptableRange < arduinoTemp && arduinoTemp < apiTemp + acceptableRange) {
            return apiTemp;
        } else {
            return arduinoTemp;
        }
    }

/*    public List<ClothingItem> getPossibleClothingItems() {
        List<ClothingItem> possibleItems = new ArrayList<>();
        for (ClothingItem clothingItem : user.getClothes()) {
            if (!clothingItem.isWash_cycle()) {
                possibleItems.add(clothingItem);
            }
        }
//        double lowestTemperature = rightTemperature(arduinoSensor.getSensorTemperature(), weatherForecast.getLowestTemperature());
        double lowestAPITemperature = weatherForecast.getLowestTemperature();
        double lowestArduinoTemperatue = arduinoSensor.getSensorTemperature();
        boolean isGoingToRain = weatherForecast.isGoingToRain();
        logger.debug("Possible items before filtering: " + Arrays.toString(possibleItems.toArray()));


        possibleItems = removeUnsuitableForTemperature(possibleItems, lowestAPITemperature, lowestArduinoTemperatue);
        possibleItems = removeUnsuitableForRain(possibleItems, isGoingToRain);
        possibleItems = removeUnsuitableForOccasion(possibleItems, occasion);

//        logger.debug("Suitable items after filtering: " + Arrays.toString(possibleItems.toArray()));
        return possibleItems;
    }

    public List<ClothingItem> removeUnsuitableForRain(List<ClothingItem> clothes, boolean isGoingToRain) {
        if (isGoingToRain) {
            aiDecision.append("It is going to rain - filtering out clothes with bad rainproofness.\n");
//            logger.debug("It is going to rain - filtering out clothes with bad rainproofness.");
            clothes = clothes
                    .stream()
                    .filter(item -> item.getRainProofness() != ClothingItem.RainProofness.BAD)
                    .collect(Collectors.toList());
        }
        return clothes;
    }

    public List<ClothingItem> removeUnsuitableForTemperature(List<ClothingItem> clothes, double APItemperature, double ArduinoTemp) {

        double checkedTemp = rightTemperature(APItemperature, ArduinoTemp);

        ClothingItem.Weather givenWeather;

        if (checkedTemp < 5) {
            aiDecision.append("The temperature is classified as cold.\n");
//            logger.debug("The temperature is classified as cold");
            givenWeather = ClothingItem.Weather.COLD;
        } else if (checkedTemp < 15) {
            aiDecision.append("The temperature is classified as mild.\n");
//            logger.debug("The temperature is classified as mild");
            givenWeather = ClothingItem.Weather.MILD;
        } else {
            aiDecision.append("The temperature is classified as warm.\n");
//            logger.debug("The temperature is classified as warm");
            givenWeather = ClothingItem.Weather.WARM;
        }

//        logger.debug("Filtering out clothes that don't fit this temperature.");
        return clothes
                .stream()
                .filter(item -> item.getWeather() == givenWeather || item.getWeather() == ClothingItem.Weather.UNIVERSAL)
                .collect(Collectors.toList());
    }

    public List<ClothingItem> removeUnsuitableForOccasion(List<ClothingItem> clothes, ClothingItem.Occasion occasion) {

        return clothes
                .stream()
                .filter(item -> item.getOccasion() == occasion || item.getOccasion() == ClothingItem.Occasion.UNIVERSAL)
                .collect(Collectors.toList());

    }

    public Map<ClothingItem.Type, List<ClothingItem>> divideClothesIntoTypes(List<ClothingItem> clothes) {

        List<ClothingItem.Type> types = new ArrayList<>(List.of(ClothingItem.Type.values()));

        Map<ClothingItem.Type, List<ClothingItem>> clothesByType = new HashMap<>();

        types.forEach(type -> {
            List<ClothingItem> clothesOfType = clothes
                    .stream()
                    .filter(item -> item.getType() == type)
                    .collect(Collectors.toList());

            clothesByType.put(type, clothesOfType);
        });

        return clothesByType;
    }

    public Map<ClothingItem.Type, List<ClothingItem>> getSuitableClothesMap() {
        Map<ClothingItem.Type, List<ClothingItem>> suitableClothes = divideClothesIntoTypes(getPossibleClothingItems());
//        logger.debug("Possible clothing items by type: " + suitableClothes.toString());
        return suitableClothes;
    }*/

    public void resetWashCycle(List<ClothingItem> allClothes, DayOfWeek washinDay){
        //TODO: WashingDay reset clothes
    }
}
