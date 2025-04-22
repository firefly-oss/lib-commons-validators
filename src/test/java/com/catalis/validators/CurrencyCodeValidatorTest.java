package com.catalis.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CurrencyCodeValidator}.
 */
class CurrencyCodeValidatorTest {

    private CurrencyCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CurrencyCodeValidator();
    }

    @Test
    void shouldReturnFalseForNullInput() {
        assertThat(validator.isValid(null)).isFalse();
    }

    @Test
    void shouldReturnFalseForEmptyInput() {
        assertThat(validator.isValid("")).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "EUR", // Euro
            "GBP", // British Pound
            "USD", // US Dollar
            "CHF", // Swiss Franc
            "JPY", // Japanese Yen
            "AUD", // Australian Dollar
            "CAD", // Canadian Dollar
            "SEK", // Swedish Krona
            "NOK", // Norwegian Krone
            "DKK"  // Danish Krone
    })
    void shouldReturnTrueForValidCurrencyCodes(String currencyCode) {
        assertThat(validator.isValid(currencyCode)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ABC", // Non-existent code
            "EU",  // Too short
            "EURO", // Too long
            "123", // Numeric code
            "US$", // Invalid characters
            "€"    // Symbol instead of code
    })
    void shouldReturnFalseForInvalidCurrencyCodes(String currencyCode) {
        assertThat(validator.isValid(currencyCode)).isFalse();
    }

    @Test
    void shouldHandleCurrencyCodeWithSpaces() {
        assertThat(validator.isValid(" EUR ")).isTrue();
    }

    @Test
    void shouldHandleCurrencyCodeWithLowercase() {
        assertThat(validator.isValid("eur")).isTrue();
    }

    @Test
    void shouldIdentifyEuroCurrency() {
        assertThat(validator.isEuroCurrency("EUR")).isTrue();
        assertThat(validator.isEuroCurrency("eur")).isTrue();
        assertThat(validator.isEuroCurrency(" EUR ")).isTrue();
    }

    @Test
    void shouldReturnFalseForNonEuroCurrency() {
        assertThat(validator.isEuroCurrency("USD")).isFalse();
        assertThat(validator.isEuroCurrency("GBP")).isFalse();
        assertThat(validator.isEuroCurrency(null)).isFalse();
        assertThat(validator.isEuroCurrency("")).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "EUR", // Euro
            "GBP", // British Pound
            "CHF", // Swiss Franc
            "SEK", // Swedish Krona
            "NOK", // Norwegian Krone
            "DKK", // Danish Krone
            "PLN", // Polish Złoty
            "CZK", // Czech Koruna
            "HUF", // Hungarian Forint
            "RON"  // Romanian Leu
    })
    void shouldIdentifyEuropeanCurrencies(String currencyCode) {
        assertThat(validator.isEuropeanCurrency(currencyCode)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "USD", // US Dollar
            "CAD", // Canadian Dollar
            "JPY", // Japanese Yen
            "AUD", // Australian Dollar
            "NZD", // New Zealand Dollar
            "CNY", // Chinese Yuan
            "INR", // Indian Rupee
            "BRL", // Brazilian Real
            "ZAR", // South African Rand
            "AED"  // UAE Dirham
    })
    void shouldReturnFalseForNonEuropeanCurrencies(String currencyCode) {
        assertThat(validator.isEuropeanCurrency(currencyCode)).isFalse();
    }

    @Test
    void shouldReturnFalseForInvalidCurrencyWhenCheckingEuropean() {
        assertThat(validator.isEuropeanCurrency(null)).isFalse();
        assertThat(validator.isEuropeanCurrency("")).isFalse();
        assertThat(validator.isEuropeanCurrency("XYZ")).isFalse();
    }
}