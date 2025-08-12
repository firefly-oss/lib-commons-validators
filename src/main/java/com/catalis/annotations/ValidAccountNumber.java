package com.catalis.annotations;

import com.catalis.validators.AccountNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAccountNumber {
    String message() default "Número de cuenta inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String countryCode() default "GB";
}