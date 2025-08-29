package com.firefly.annotations;

import com.firefly.validators.TaxIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid tax identification number.
 * Supported tax ID types include:
 * - US TIN (Tax Identification Number)
 * - Mexico RFC (Registro Federal de Contribuyentes)
 * - Argentina CUIT (Código Único de Identificación Tributaria)
 * - Spain NIF (Número de Identificación Fiscal)
 * - And others depending on the country
 */
@Documented
@Constraint(validatedBy = TaxIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaxId {
    String message() default "Invalid tax identification number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the country code (ISO 3166-1 alpha-2) for country-specific validation.
     * If not specified, the validator will attempt to determine the country from the format.
     */
    String country() default "";
}