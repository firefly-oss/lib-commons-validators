package com.catalis.annotations;

import com.catalis.validators.CVVValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid CVV (Card Verification Value).
 * By default, validates that the CVV is 3 or 4 digits.
 */
@Documented
@Constraint(validatedBy = CVVValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCVV {
    String message() default "Invalid CVV";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the card type to validate against.
     * Different card types have different CVV formats.
     * If not specified, accepts both 3 and 4 digit CVVs.
     */
    CardType cardType() default CardType.ANY;
    
    /**
     * Card types with their corresponding CVV formats.
     */
    enum CardType {
        /**
         * Any card type (accepts 3 or 4 digit CVVs).
         */
        ANY,
        
        /**
         * Visa, MasterCard, Discover (3 digit CVV).
         */
        STANDARD,
        
        /**
         * American Express (4 digit CVV).
         */
        AMEX
    }
}