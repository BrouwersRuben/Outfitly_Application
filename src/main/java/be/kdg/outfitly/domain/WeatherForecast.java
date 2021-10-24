package be.kdg.outfitly.domain;

import java.time.LocalDate;

public class WeatherForecast extends Entity {
    private LocalDate start;
    private LocalDate end;
    //TODO: check what is returned by the API

    private double getLowestTemperature(){
        //TODO

        return 0.0;
    }

    private boolean isGoingToRain(){
        //TODO

        return false;
    }


}
