package com.mercadonarest.infraestructure.postgres.entities.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BarcodeSizeValidator implements
        ConstraintValidator<BarcodeSizeConstraint, Long> {

    @Override
    public void initialize(BarcodeSizeConstraint barcodeSizeConstraint) {
    }

    @Override
    public boolean isValid(Long contactFieldLong,
                           ConstraintValidatorContext cxt) {
        String contactField = contactFieldLong.toString();
        return contactField.length() == 13;
    }

}
