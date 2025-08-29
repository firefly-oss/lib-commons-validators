package com.firefly.annotations;

import com.firefly.validators.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated element must be a password with sufficient strength.
 * By default, validates that the password has at least 8 characters,
 * including at least one uppercase letter, one lowercase letter,
 * one digit, and one special character.
 */
@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPasswordStrength {
    String message() default "Password is not strong enough";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Specifies the minimum length of the password.
     * Default is 8 characters.
     */
    int minLength() default 8;
    
    /**
     * Specifies the maximum length of the password.
     * Default is 128 characters.
     */
    int maxLength() default 128;
    
    /**
     * Specifies whether the password must contain at least one uppercase letter.
     * Default is true.
     */
    boolean requireUppercase() default true;
    
    /**
     * Specifies whether the password must contain at least one lowercase letter.
     * Default is true.
     */
    boolean requireLowercase() default true;
    
    /**
     * Specifies whether the password must contain at least one digit.
     * Default is true.
     */
    boolean requireDigit() default true;
    
    /**
     * Specifies whether the password must contain at least one special character.
     * Default is true.
     */
    boolean requireSpecialChar() default true;
    
    /**
     * Specifies the minimum number of character types required.
     * Character types are: uppercase letters, lowercase letters, digits, and special characters.
     * Default is 3 types.
     */
    int minCharTypes() default 3;
    
    /**
     * Specifies whether to check for common/weak passwords.
     * Default is true, meaning common passwords are rejected.
     */
    boolean rejectCommonPasswords() default true;
}