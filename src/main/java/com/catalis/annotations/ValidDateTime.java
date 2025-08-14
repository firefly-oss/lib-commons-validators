package com.catalis.annotations;

import com.catalis.validators.DateTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a valid date-time.
 * By default, validates date-times in ISO format (yyyy-MM-dd HH:mm:ss).
 */
@Documented
@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateTime {
    String message() default "Invalid date-time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the date-time format pattern.
     * Default is ISO format (yyyy-MM-dd HH:mm:ss).
     * See java.text.SimpleDateFormat for pattern syntax.
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";
    
    /**
     * Specifies the minimum date-time (inclusive) in the same format as the pattern.
     * Default is empty, meaning there is no minimum date-time.
     */
    String min() default "";
    
    /**
     * Specifies the maximum date-time (inclusive) in the same format as the pattern.
     * Default is empty, meaning there is no maximum date-time.
     */
    String max() default "";
    
    /**
     * Specifies whether future date-times are allowed.
     * Default is true, meaning future date-times are allowed.
     */
    boolean allowFuture() default true;
    
    /**
     * Specifies whether past date-times are allowed.
     * Default is true, meaning past date-times are allowed.
     */
    boolean allowPast() default true;
}