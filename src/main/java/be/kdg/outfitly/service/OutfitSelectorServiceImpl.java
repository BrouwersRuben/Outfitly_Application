package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutfitSelectorServiceImpl implements OutfitSelectorService {

    private final WeatherForecastService weatherForecastService;

    private StringBuilder aiDecision = new StringBuilder();

    public OutfitSelectorServiceImpl(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    //Return all possible clothing items based on the weather and occasion picked by the user
    public List<ClothingItem> getPossibleClothingItems(User user, ClothingItem.Occasion occasion) {
        WeatherForecast weatherForecast = weatherForecastService.getNewByCountryCodeAndCity(user.getCountryCode(), user.getCity());

        List<ClothingItem> possibleItems = user.getClothes();
        possibleItems.removeIf(ClothingItem::isWashCycle);

        boolean isGoingToRain = weatherForecast.isGoingToRain();
        double lowestTemperature = weatherForecast.getLowestTemperature();

        possibleItems = removeUnsuitableForTemperature(possibleItems, lowestTemperature);
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

    public List<ClothingItem> removeUnsuitableForTemperature(List<ClothingItem> clothes, double temperature) {

        ClothingItem.Weather givenWeather;

        if (temperature < 5) {
            aiDecision.append("The temperature is classified as cold.\n");
            givenWeather = ClothingItem.Weather.COLD;
        } else if (temperature < 15) {
            aiDecision.append("The temperature is classified as mild.\n");
            givenWeather = ClothingItem.Weather.MILD;
        } else {
            aiDecision.append("The temperature is classified as warm.\n");
            givenWeather = ClothingItem.Weather.WARM;
        }

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

    @Override
    public Map<ClothingItem.Type, List<ClothingItem>> getSuitableClothesGroupedByType(User user, ClothingItem.Occasion occasion) {
        return divideClothesIntoTypes(getPossibleClothingItems(user, occasion));
    }

    @Override
    public String getAiDecision() {
        String aiDec = aiDecision.toString();
        aiDecision.delete(0, aiDecision.length()-1);
        return aiDec;
    }
}
