package be.kdg.outfitly.presentation.dto;

import be.kdg.outfitly.domain.WeatherForecast;
import be.kdg.outfitly.presentation.dto.profileChanges.LocationDTO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocationValidator implements ConstraintValidator<LocationValidation, Locatable> {

    @Override
    public void initialize(LocationValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Locatable value, ConstraintValidatorContext context) {
        boolean valid = WeatherForecast.isValidCountryCity(value.getCountryCode(), value.getCity());
//        if(!valid){
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
//                    .addPropertyNode("invalidLocation").addConstraintViolation();
//        }
        return valid;
    }




}
