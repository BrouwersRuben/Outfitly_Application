package be.kdg.outfitly.presentation.dto;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocationValidation {
    String message() default "Location not found.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
