package be.kdg.outfitly.domain;

import java.util.List;
import java.util.stream.Collectors;

public class OutfitSelector {
    private WeatherForecast weatherForecast;
    private User user;



    public List<ClothingItem> getPossibleClothingItems() {

        List<ClothingItem> possibleItems = user.getClothes();
        double lowestTemperature = weatherForecast.getLowestTemperature();
        boolean isGoingToRain = weatherForecast.isGoingToRain();

        //TODO: this has to be passed from somewhere else
        ClothingItem.Occasion occasion = ClothingItem.Occasion.UNIVERSAL;

        possibleItems = removeUnsuitableForTemperature(possibleItems, lowestTemperature);
        possibleItems = removeUnsuitableForRain(possibleItems, isGoingToRain);
        possibleItems = removeUnsuitableForOccasion(possibleItems, occasion);

        return possibleItems;
    }


    public List<ClothingItem> removeUnsuitableForRain(List<ClothingItem> clothes, boolean isGoingToRain) {
        if (isGoingToRain) {
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
           givenWeather = ClothingItem.Weather.COLD;
        } else if(temperature < 15){
            givenWeather = ClothingItem.Weather.MILD;
        } else {
            givenWeather = ClothingItem.Weather.WARM;
        }

        return clothes
                .stream()
                .filter(item -> item.getWeather() == givenWeather || item.getWeather() == ClothingItem.Weather.UNIVERSAL)
                .collect(Collectors.toList());
    }

    public List<ClothingItem> removeUnsuitableForOccasion(List<ClothingItem> clothes, ClothingItem.Occasion occasion){

        return clothes
                .stream()
                .filter(item -> item.getOccasion() == occasion || item.getOccasion() == ClothingItem.Occasion.UNIVERSAL)
                .collect(Collectors.toList());

    }

}
