package com.catalis.annotations;

import com.catalis.validators.CurrencyCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyCode {
    String message() default "Código de moneda inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean europeanOnly() default false;
    boolean euroOnly() default false;
}