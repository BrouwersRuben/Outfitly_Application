package be.kdg.outfitly.presentation.dto;

import be.kdg.outfitly.domain.WeatherForecast;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocationValidator implements ConstraintValidator<LocationValidation, Locatable> {

    @Override
    public void initialize(LocationValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Locatable value, ConstraintValidatorContext context) {
        return WeatherForecast.isValidCountryCity(value.getCountryCode(), value.getCity());
    }


}
