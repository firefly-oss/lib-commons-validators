package com.catalis.validators;

import java.util.HashSet;
import java.util.Set;

/**
 * Validator for ISO 4217 currency codes.
 * 
 * This validator checks if a currency code is valid according to the ISO 4217 standard,
 * which defines three-letter codes for currencies used in international transactions.
 */
public class CurrencyCodeValidator {
    
    // Set of valid ISO 4217 currency codes
    private static final Set<String> VALID_CURRENCY_CODES = new HashSet<>();
    
    static {
        // European currencies
        VALID_CURRENCY_CODES.add("EUR"); // Euro
        VALID_CURRENCY_CODES.add("GBP"); // British Pound
        VALID_CURRENCY_CODES.add("CHF"); // Swiss Franc
        VALID_CURRENCY_CODES.add("SEK"); // Swedish Krona
        VALID_CURRENCY_CODES.add("NOK"); // Norwegian Krone
        VALID_CURRENCY_CODES.add("DKK"); // Danish Krone
        VALID_CURRENCY_CODES.add("PLN"); // Polish Złoty
        VALID_CURRENCY_CODES.add("CZK"); // Czech Koruna
        VALID_CURRENCY_CODES.add("HUF"); // Hungarian Forint
        VALID_CURRENCY_CODES.add("RON"); // Romanian Leu
        VALID_CURRENCY_CODES.add("BGN"); // Bulgarian Lev
        VALID_CURRENCY_CODES.add("HRK"); // Croatian Kuna
        VALID_CURRENCY_CODES.add("ISK"); // Icelandic Króna
        
        // Major world currencies
        VALID_CURRENCY_CODES.add("USD"); // US Dollar
        VALID_CURRENCY_CODES.add("CAD"); // Canadian Dollar
        VALID_CURRENCY_CODES.add("AUD"); // Australian Dollar
        VALID_CURRENCY_CODES.add("NZD"); // New Zealand Dollar
        VALID_CURRENCY_CODES.add("JPY"); // Japanese Yen
        VALID_CURRENCY_CODES.add("CNY"); // Chinese Yuan
        VALID_CURRENCY_CODES.add("HKD"); // Hong Kong Dollar
        VALID_CURRENCY_CODES.add("SGD"); // Singapore Dollar
        VALID_CURRENCY_CODES.add("INR"); // Indian Rupee
        VALID_CURRENCY_CODES.add("RUB"); // Russian Ruble
        VALID_CURRENCY_CODES.add("ZAR"); // South African Rand
        VALID_CURRENCY_CODES.add("BRL"); // Brazilian Real
        VALID_CURRENCY_CODES.add("MXN"); // Mexican Peso
        
        // Middle Eastern currencies
        VALID_CURRENCY_CODES.add("AED"); // UAE Dirham
        VALID_CURRENCY_CODES.add("SAR"); // Saudi Riyal
        VALID_CURRENCY_CODES.add("QAR"); // Qatari Riyal
        VALID_CURRENCY_CODES.add("ILS"); // Israeli New Shekel
        
        // Cryptocurrencies with ISO codes
        VALID_CURRENCY_CODES.add("XBT"); // Bitcoin
        VALID_CURRENCY_CODES.add("XET"); // Ethereum
    }
    
    /**
     * Validates if the provided currency code is valid according to ISO 4217.
     *
     * @param currencyCode the currency code to validate
     * @return true if the currency code is valid, false otherwise
     */
    public boolean isValid(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return false;
        }
        
        // Convert to uppercase for case-insensitive comparison
        String normalizedCode = currencyCode.trim().toUpperCase();
        
        // Check if it's in our set of valid codes
        return VALID_CURRENCY_CODES.contains(normalizedCode);
    }
    
    /**
     * Checks if the currency is a European Union currency (EUR).
     *
     * @param currencyCode the currency code to check
     * @return true if the currency is EUR, false otherwise
     */
    public boolean isEuroCurrency(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return false;
        }
        
        String normalizedCode = currencyCode.trim().toUpperCase();
        return "EUR".equals(normalizedCode);
    }
    
    /**
     * Checks if the currency is from a European country.
     *
     * @param currencyCode the currency code to check
     * @return true if the currency is European, false otherwise
     */
    public boolean isEuropeanCurrency(String currencyCode) {
        if (!isValid(currencyCode)) {
            return false;
        }
        
        String normalizedCode = currencyCode.trim().toUpperCase();
        
        // Set of European currency codes
        Set<String> europeanCurrencies = new HashSet<>();
        europeanCurrencies.add("EUR"); // Euro
        europeanCurrencies.add("GBP"); // British Pound
        europeanCurrencies.add("CHF"); // Swiss Franc
        europeanCurrencies.add("SEK"); // Swedish Krona
        europeanCurrencies.add("NOK"); // Norwegian Krone
        europeanCurrencies.add("DKK"); // Danish Krone
        europeanCurrencies.add("PLN"); // Polish Złoty
        europeanCurrencies.add("CZK"); // Czech Koruna
        europeanCurrencies.add("HUF"); // Hungarian Forint
        europeanCurrencies.add("RON"); // Romanian Leu
        europeanCurrencies.add("BGN"); // Bulgarian Lev
        europeanCurrencies.add("HRK"); // Croatian Kuna
        europeanCurrencies.add("ISK"); // Icelandic Króna
        
        return europeanCurrencies.contains(normalizedCode);
    }
}