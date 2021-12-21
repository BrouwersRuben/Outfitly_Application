package be.kdg.outfitly.presentation.dto;

import be.kdg.outfitly.service.WeatherForecastService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocationValidator implements ConstraintValidator<LocationValidation, Locatable> {

    private WeatherForecastService weatherForecastService;

    public LocationValidator(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @Override
    public void initialize(LocationValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Locatable value, ConstraintValidatorContext context) {

        return weatherForecastService.isValidCountryCodeAndCity(value.getCountryCode(), value.getCity());
//        return WeatherForecast.isValidCountryCity(value.getCountryCode(), value.getCity());
    }


}
