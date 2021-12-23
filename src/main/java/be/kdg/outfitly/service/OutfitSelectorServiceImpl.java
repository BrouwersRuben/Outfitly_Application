package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutfitSelectorServiceImpl implements OutfitSelectorService {

    //TODO: Logger
    private final ArduinoSensorService arduinoSensorService;
    private final WeatherForecastService weatherForecastService;

    private StringBuilder aiDecision = new StringBuilder();

    public OutfitSelectorServiceImpl(ArduinoSensorService arduinoSensorService, WeatherForecastService weatherForecastService) {
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

    public List<ClothingItem> getPossibleClothingItems(User user, ClothingItem.Occasion occasion) {
        WeatherForecast weatherForecast = weatherForecastService.findByCountryAndCity(user.getCountryCode(), user.getCity());
        //TODO: is this the correct way to do the time
        ArduinoSensor arduinoSensor = arduinoSensorService.findByUser(user, LocalDateTime.now());
        List<ClothingItem> possibleItems = user.getClothes();
        double lowestTemperature = weatherForecast.getLowestTemperature();
        double lowestArduinoTemperatue = arduinoSensor.getSensorTemperature();
        boolean isGoingToRain = weatherForecast.isGoingToRain();

        possibleItems = removeUnsuitableForTemperature(possibleItems, lowestTemperature,lowestArduinoTemperatue);
        possibleItems = removeUnsuitableForRain(possibleItems, isGoingToRain);
        possibleItems = removeUnsuitableForOccasion(possibleItems, occasion);

        return possibleItems;
    }

    public List<ClothingItem> removeUnsuitableForOccasion(List<ClothingItem> clothes, ClothingItem.Occasion occasion) {

        return clothes
                .stream()
                .filter(item -> item.getOccasion() == occasion || item.getOccasion() == ClothingItem.Occasion.UNIVERSAL)
                .collect(Collectors.toList());

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

    public List<ClothingItem> removeUnsuitableForRain(List<ClothingItem> clothes, boolean isGoingToRain) {
        if (isGoingToRain) {
            aiDecision.append("It is going to rain - filtering out clothes with bad rainproofness.\n");
            clothes = clothes
                    .stream()
                    .filter(item -> item.getRainProofness() != ClothingItem.RainProofness.BAD)
                    .collect(Collectors.toList());
        }
        return clothes;
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

    //TODO: Redundant
    @Override
    public Map<ClothingItem.Type, List<ClothingItem>> getSuitableClothes(User user, ClothingItem.Occasion occasion) {
        Map<ClothingItem.Type, List<ClothingItem>> suitableClothes = divideClothesIntoTypes(getPossibleClothingItems(user, occasion));
        return suitableClothes;
    }

    @Override
    public String getAiDecision() {
        String aiDec = aiDecision.toString();
        aiDecision.delete(0, aiDecision.length()-1);
        return aiDec;
    }
}
