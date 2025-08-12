package com.catalis.annotations;

import com.catalis.validators.AmountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid monetary amount.
 * By default, validates that the amount is a positive decimal number
 * with the appropriate number of decimal places for the specified currency.
 */
@Documented
@Constraint(validatedBy = AmountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAmount {
    String message() default "Invalid monetary amount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the currency code (ISO 4217) for currency-specific validation.
     * This determines the maximum number of decimal places allowed.
     */
    String currency() default "EUR";
    
    /**
     * Specifies the minimum value (inclusive) that the amount can have.
     * Default is 0, meaning the amount must be non-negative.
     */
    double min() default 0.0;
    
    /**
     * Specifies the maximum value (inclusive) that the amount can have.
     * Default is Double.MAX_VALUE, meaning there is no upper limit.
     */
    double max() default Double.MAX_VALUE;
    
    /**
     * Specifies whether zero is allowed as a valid amount.
     * Default is true, meaning zero is a valid amount.
     */
    boolean allowZero() default true;
}