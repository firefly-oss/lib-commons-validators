package com.catalis.annotations;

import com.catalis.validators.BicValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BicValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBic {
    String message() default "BIC inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}