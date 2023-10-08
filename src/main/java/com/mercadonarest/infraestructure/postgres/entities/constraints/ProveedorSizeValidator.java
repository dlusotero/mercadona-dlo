package com.mercadonarest.infraestructure.postgres.entities.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProveedorSizeValidator implements
        ConstraintValidator<ProveedorSizeConstraint, Long> {

    @Override
    public void initialize(ProveedorSizeConstraint barcodeSizeConstraint) {
    }

    @Override
    public boolean isValid(Long contactFieldLong,
                           ConstraintValidatorContext cxt) {
        String contactField = contactFieldLong.toString();
        return contactField.length() == 7;
    }

}
