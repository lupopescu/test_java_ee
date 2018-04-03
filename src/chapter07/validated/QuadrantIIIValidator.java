package chapter07.validated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuadrantIIIValidator implements ConstraintValidator<NoQuadrantIII, Coordinate> {
    @Override
    public void initialize(NoQuadrantIII constraintAnnotation) {
    }

    @Override
    public boolean isValid(Coordinate value, ConstraintValidatorContext context) {
        return !(value.x < 0 && value.y < 0);
    }

   
}
