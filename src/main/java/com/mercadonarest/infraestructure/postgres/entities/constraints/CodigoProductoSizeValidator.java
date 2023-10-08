package com.mercadonarest.infraestructure.postgres.entities.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodigoProductoSizeValidator implements
        ConstraintValidator<CodigoProductoSizeConstraint, Long> {

    @Override
    public void initialize(CodigoProductoSizeConstraint barcodeSizeConstraint) {
    }

    @Override
    public boolean isValid(Long contactFieldLong,
                           ConstraintValidatorContext cxt) {
        String contactField = contactFieldLong.toString();
        return contactField.length() == 5;
    }

}
