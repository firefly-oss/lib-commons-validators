package com.firefly.annotations;

import com.firefly.validators.CreditCardValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCreditCard {
    String message() default "Número de tarjeta de crédito inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    CreditCardValidator.CardType[] types() default {};
}