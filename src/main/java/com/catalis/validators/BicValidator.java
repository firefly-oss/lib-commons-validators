package com.catalis.validators;

import java.util.regex.Pattern;

/**
 * Validator for Bank Identifier Codes (BIC), also known as SWIFT codes.
 * 
 * This validator checks if a BIC is valid according to the standard format
 * which includes:
 * - Bank code (4 letters)
 * - Country code (2 letters)
 * - Location code (2 letters or digits)
 * - Optional branch code (3 letters or digits)
 */
public class BicValidator {
    
    // BIC format: bank code (4 letters) + country code (2 letters) + location code (2 letters/digits) + optional branch code (3 letters/digits)
    private static final Pattern BIC_8_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}$");
    private static final Pattern BIC_11_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}[A-Z0-9]{3}$");
    
    /**
     * Validates if the provided BIC is valid.
     *
     * @param bic the BIC to validate
     * @return true if the BIC is valid, false otherwise
     */
    public boolean isValid(String bic) {
        if (bic == null || bic.isEmpty()) {
            return false;
        }
        
        // Remove spaces and convert to uppercase
        String normalizedBic = bic.replaceAll("\\s", "").toUpperCase();
        
        // BIC can be either 8 or 11 characters long
        int length = normalizedBic.length();
        if (length != 8 && length != 11) {
            return false;
        }
        
        // Check format based on length
        if (length == 8) {
            return BIC_8_PATTERN.matcher(normalizedBic).matches();
        } else {
            return BIC_11_PATTERN.matcher(normalizedBic).matches();
        }
    }
    
    /**
     * Extracts the bank code from a valid BIC.
     *
     * @param bic the BIC to extract from
     * @return the bank code or null if the BIC is invalid
     */
    public String getBankCode(String bic) {
        if (!isValid(bic)) {
            return null;
        }
        
        String normalizedBic = bic.replaceAll("\\s", "").toUpperCase();
        return normalizedBic.substring(0, 4);
    }
    
    /**
     * Extracts the country code from a valid BIC.
     *
     * @param bic the BIC to extract from
     * @return the country code or null if the BIC is invalid
     */
    public String getCountryCode(String bic) {
        if (!isValid(bic)) {
            return null;
        }
        
        String normalizedBic = bic.replaceAll("\\s", "").toUpperCase();
        return normalizedBic.substring(4, 6);
    }
    
    /**
     * Extracts the location code from a valid BIC.
     *
     * @param bic the BIC to extract from
     * @return the location code or null if the BIC is invalid
     */
    public String getLocationCode(String bic) {
        if (!isValid(bic)) {
            return null;
        }
        
        String normalizedBic = bic.replaceAll("\\s", "").toUpperCase();
        return normalizedBic.substring(6, 8);
    }
    
    /**
     * Extracts the branch code from a valid BIC.
     *
     * @param bic the BIC to extract from
     * @return the branch code, "XXX" if not specified, or null if the BIC is invalid
     */
    public String getBranchCode(String bic) {
        if (!isValid(bic)) {
            return null;
        }
        
        String normalizedBic = bic.replaceAll("\\s", "").toUpperCase();
        if (normalizedBic.length() == 8) {
            return "XXX"; // Default branch code
        } else {
            return normalizedBic.substring(8, 11);
        }
    }
}