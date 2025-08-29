package com.firefly.annotations;

import com.firefly.validators.SortCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SortCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortCode {
    String message() default "Código de ordenación bancaria inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}