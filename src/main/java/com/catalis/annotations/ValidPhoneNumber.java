package com.catalis.annotations;

import com.catalis.validators.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid phone number.
 * By default, validates phone numbers in the international E.164 format.
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies whether the phone number must be in E.164 format.
     * E.164 format: +[country code][number] without spaces or other separators.
     * Example: +14155552671
     */
    boolean e164Format() default true;
    
    /**
     * Specifies the country code (ISO 3166-1 alpha-2) for country-specific validation.
     * If not specified, the validator will attempt to determine the country from the number.
     */
    String country() default "";
}