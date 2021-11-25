package be.kdg.outfitly.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class OutfitSelector {
    private final WeatherForecast weatherForecast;
    private ArduinoSensor arduinoSensor;
    private final User user;
    private final ClothingItem.Occasion occasion;

    public OutfitSelector(WeatherForecast weatherForecast, User user, ClothingItem.Occasion occasion) {
        this.weatherForecast = weatherForecast;
        this.user = user;
        this.occasion = occasion;
    }

    private final Logger logger = LoggerFactory.getLogger(OutfitSelector.class);

    private double rightTemperature(double arduinoTemp, double apiTemp){
        final double acceptableRange = 3; //the api can be 3° over or under the captured temperature of the arduino to still be counted as correct.
        if(apiTemp - acceptableRange < arduinoTemp && arduinoTemp < apiTemp + acceptableRange){
            return apiTemp;
        }else{
            return arduinoTemp;
        }
    }

    public List<ClothingItem> getPossibleClothingItems() {

        List<ClothingItem> possibleItems = user.getClothes();
        //TODO:
//        double lowestTemperature = rightTemperature(arduinoSensor.getSensorTemperature(), weatherForecast.getLowestTemperature());
        double lowestTemperature = weatherForecast.getLowestTemperature();
        boolean isGoingToRain = weatherForecast.isGoingToRain();
        logger.debug("Possible items: "+ Arrays.toString(possibleItems.toArray()));


        possibleItems = removeUnsuitableForTemperature(possibleItems, lowestTemperature);
        possibleItems = removeUnsuitableForRain(possibleItems, isGoingToRain);
        possibleItems = removeUnsuitableForOccasion(possibleItems, occasion);

        logger.debug("Possible items: "+ Arrays.toString(possibleItems.toArray()));
        return possibleItems;
    }


    public List<ClothingItem> removeUnsuitableForRain(List<ClothingItem> clothes, boolean isGoingToRain) {
        if (isGoingToRain) {
            logger.debug("It is going to rain, so it chose rainproof clothing");
            clothes = clothes
                    .stream()
                    .filter(item -> item.getRainProofness() != ClothingItem.RainProofness.BAD)
                    .collect(Collectors.toList());
        }
        return clothes;
    }

    public List<ClothingItem> removeUnsuitableForTemperature(List<ClothingItem> clothes, double temperature) {

        ClothingItem.Weather givenWeather;


        if (temperature < 5) {
            logger.debug("The weather is cold, so it chose clothing for cold weather");
            givenWeather = ClothingItem.Weather.COLD;
        } else if (temperature < 15) {
            logger.debug("The weather is mild, so it chose clothing for cold mild");
            givenWeather = ClothingItem.Weather.MILD;
        } else {
            logger.debug("The weather is warm, so it chose clothing for warm weather");
            givenWeather = ClothingItem.Weather.WARM;
        }

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

        types.stream()
                .forEach(type -> {
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
        logger.debug(suitableClothes.toString());
        return suitableClothes;
    }

}
