package be.kdg.outfitly.domain;

import java.time.LocalDate;

public class WeatherForecast extends Entity {
    private LocalDate start;
    private LocalDate end;
    //TODO: check what is returned by the API

    public double getLowestTemperature(){
        //TODO

        return 0.0;
    }

    public boolean isGoingToRain(){
        //TODO

        return false;
    }


}
