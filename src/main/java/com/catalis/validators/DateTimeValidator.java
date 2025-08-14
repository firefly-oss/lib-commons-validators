package com.catalis.validators;

import com.catalis.annotations.ValidDateTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Validator for date-time strings.
 *
 * This validator checks if a date-time is valid according to:
 * - Format pattern
 * - Min/max constraints (inclusive)
 * - Future/past constraints relative to current system time
 */
public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {

    private String pattern;
    private String min;
    private String max;
    private boolean allowFuture;
    private boolean allowPast;

    @Override
    public void initialize(ValidDateTime constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.allowFuture = constraintAnnotation.allowFuture();
        this.allowPast = constraintAnnotation.allowPast();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
            sdf.setLenient(false); // strict parsing
            Date dateTime = sdf.parse(value);

            // Min constraint (inclusive)
            if (!min.isEmpty()) {
                Date minDt = sdf.parse(min);
                if (dateTime.before(minDt)) {
                    return false;
                }
            }

            // Max constraint (inclusive)
            if (!max.isEmpty()) {
                Date maxDt = sdf.parse(max);
                if (dateTime.after(maxDt)) {
                    return false;
                }
            }

            Date now = new Date();

            if (!allowFuture && dateTime.after(now)) {
                return false;
            }
            return allowPast || !dateTime.before(now);
        } catch (ParseException e) {
            return false;
        }
    }
}
