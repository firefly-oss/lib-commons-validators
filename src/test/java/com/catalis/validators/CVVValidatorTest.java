package com.catalis.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CVVValidator}.
 */
class CVVValidatorTest {

    private CVVValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CVVValidator();
        // Initialize with default values (cardType=ANY)
        validator.initialize(new com.catalis.annotations.ValidCVV() {
            @Override
            public Class<?>[] groups() { return new Class<?>[0]; }
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() { 
                return com.catalis.annotations.ValidCVV.class; 
            }
            @Override
            public String message() { return "Invalid CVV"; }
            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() { 
                return new Class[0]; 
            }
            @Override
            public com.catalis.annotations.ValidCVV.CardType cardType() { 
                return com.catalis.annotations.ValidCVV.CardType.ANY; 
            }
        });
    }

    @Test
    void shouldReturnFalseForNullInput() {
        assertThat(validator.isValid(null, null)).isFalse();
    }

    @Test
    void shouldReturnFalseForEmptyInput() {
        assertThat(validator.isValid("", null)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123",  // 3 digits (standard cards)
            "1234"  // 4 digits (American Express)
    })
    void shouldReturnTrueForValidCVVs(String cvv) {
        assertThat(validator.isValid(cvv, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12",    // Too short
            "12345", // Too long
            "12A",   // Contains letters
            "1-3",   // Contains special characters
            "1 2"    // Contains spaces
    })
    void shouldReturnFalseForInvalidCVVs(String cvv) {
        assertThat(validator.isValid(cvv, null)).isFalse();
    }

    @Test
    void shouldValidateStandardCardCVV() {
        // Create validator for standard cards (Visa, MasterCard, Discover)
        CVVValidator standardValidator = new CVVValidator();
        standardValidator.initialize(new com.catalis.annotations.ValidCVV() {
            @Override
            public Class<?>[] groups() { return new Class<?>[0]; }
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() { 
                return com.catalis.annotations.ValidCVV.class; 
            }
            @Override
            public String message() { return "Invalid CVV"; }
            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() { 
                return new Class[0]; 
            }
            @Override
            public com.catalis.annotations.ValidCVV.CardType cardType() { 
                return com.catalis.annotations.ValidCVV.CardType.STANDARD; 
            }
        });
        
        assertThat(standardValidator.isValid("123", null)).isTrue();
        assertThat(standardValidator.isValid("1234", null)).isFalse(); // Too long for standard cards
    }

    @Test
    void shouldValidateAmexCardCVV() {
        // Create validator for American Express cards
        CVVValidator amexValidator = new CVVValidator();
        amexValidator.initialize(new com.catalis.annotations.ValidCVV() {
            @Override
            public Class<?>[] groups() { return new Class<?>[0]; }
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() { 
                return com.catalis.annotations.ValidCVV.class; 
            }
            @Override
            public String message() { return "Invalid CVV"; }
            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() { 
                return new Class[0]; 
            }
            @Override
            public com.catalis.annotations.ValidCVV.CardType cardType() { 
                return com.catalis.annotations.ValidCVV.CardType.AMEX; 
            }
        });
        
        assertThat(amexValidator.isValid("1234", null)).isTrue();
        assertThat(amexValidator.isValid("123", null)).isFalse(); // Too short for Amex
    }

    @Test
    void shouldValidateForCardType() {
        // Test isValidForCardType method
        assertThat(validator.isValidForCardType("123", com.catalis.annotations.ValidCVV.CardType.STANDARD)).isTrue();
        assertThat(validator.isValidForCardType("1234", com.catalis.annotations.ValidCVV.CardType.AMEX)).isTrue();
        
        assertThat(validator.isValidForCardType("123", com.catalis.annotations.ValidCVV.CardType.AMEX)).isFalse();
        assertThat(validator.isValidForCardType("1234", com.catalis.annotations.ValidCVV.CardType.STANDARD)).isFalse();
        
        // ANY card type accepts both 3 and 4 digits
        assertThat(validator.isValidForCardType("123", com.catalis.annotations.ValidCVV.CardType.ANY)).isTrue();
        assertThat(validator.isValidForCardType("1234", com.catalis.annotations.ValidCVV.CardType.ANY)).isTrue();
        
        // Invalid inputs
        assertThat(validator.isValidForCardType(null, com.catalis.annotations.ValidCVV.CardType.ANY)).isFalse();
        assertThat(validator.isValidForCardType("", com.catalis.annotations.ValidCVV.CardType.ANY)).isFalse();
    }

    @Test
    void shouldValidateForCardNumber() {
        // Test isValidForCardNumber method
        // Visa (starts with 4) - should have 3-digit CVV
        assertThat(validator.isValidForCardNumber("123", "4111111111111111")).isTrue();
        assertThat(validator.isValidForCardNumber("1234", "4111111111111111")).isFalse();
        
        // American Express (starts with 34 or 37) - should have 4-digit CVV
        assertThat(validator.isValidForCardNumber("1234", "341111111111111")).isTrue();
        assertThat(validator.isValidForCardNumber("123", "341111111111111")).isFalse();
        
        // MasterCard (starts with 51-55) - should have 3-digit CVV
        assertThat(validator.isValidForCardNumber("123", "5111111111111111")).isTrue();
        assertThat(validator.isValidForCardNumber("1234", "5111111111111111")).isFalse();
        
        // Invalid inputs
        assertThat(validator.isValidForCardNumber(null, "4111111111111111")).isFalse();
        assertThat(validator.isValidForCardNumber("123", null)).isFalse();
        assertThat(validator.isValidForCardNumber("", "4111111111111111")).isFalse();
        assertThat(validator.isValidForCardNumber("123", "")).isFalse();
    }

    @Test
    void shouldMaskCVV() {
        assertThat(validator.maskCVV("123")).isEqualTo("***");
        assertThat(validator.maskCVV("1234")).isEqualTo("****");
        
        // Invalid inputs
        assertThat(validator.maskCVV(null)).isNull();
        assertThat(validator.maskCVV("")).isNull();
        assertThat(validator.maskCVV("12")).isNull(); // Too short
        assertThat(validator.maskCVV("12345")).isNull(); // Too long
    }
}