package com.catalis.annotations;

import com.catalis.validators.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid date.
 * By default, validates dates in ISO format (yyyy-MM-dd).
 */
@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
    String message() default "Invalid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the date format pattern.
     * Default is ISO format (yyyy-MM-dd).
     * See java.text.SimpleDateFormat for pattern syntax.
     */
    String pattern() default "yyyy-MM-dd";
    
    /**
     * Specifies the minimum date (inclusive) in the same format as the pattern.
     * Default is empty, meaning there is no minimum date.
     */
    String min() default "";
    
    /**
     * Specifies the maximum date (inclusive) in the same format as the pattern.
     * Default is empty, meaning there is no maximum date.
     */
    String max() default "";
    
    /**
     * Specifies whether future dates are allowed.
     * Default is true, meaning future dates are allowed.
     */
    boolean allowFuture() default true;
    
    /**
     * Specifies whether past dates are allowed.
     * Default is true, meaning past dates are allowed.
     */
    boolean allowPast() default true;
}