package com.firefly.annotations;

import com.firefly.validators.NationalIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid national identification document number.
 * Supported document types include:
 * - Spanish DNI/NIE
 * - Brazilian CPF
 * - US SSN
 * - UK National Insurance Number
 * - And others depending on the country
 */
@Documented
@Constraint(validatedBy = NationalIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNationalId {
    String message() default "Invalid national identification number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the country code (ISO 3166-1 alpha-2) for country-specific validation.
     * If not specified, the validator will attempt to determine the country from the format.
     */
    String country() default "";
}