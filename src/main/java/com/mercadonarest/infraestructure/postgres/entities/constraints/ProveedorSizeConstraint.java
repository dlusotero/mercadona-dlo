package com.mercadonarest.infraestructure.postgres.entities.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProveedorSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProveedorSizeConstraint {
    String message() default "El código tiene que tener 7 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
