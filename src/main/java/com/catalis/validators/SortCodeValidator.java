package com.catalis.validators;

import java.util.regex.Pattern;

/**
 * Validator for UK bank sort codes.
 * 
 * This validator checks if a sort code is valid according to the UK banking standard,
 * which defines sort codes as 6-digit numbers used to identify banks and branches.
 * Sort codes are typically formatted as "XX-XX-XX" where X is a digit.
 */
public class SortCodeValidator {
    
    // Sort code format: 6 digits, optionally separated by hyphens or spaces
    private static final Pattern SORT_CODE_PATTERN = Pattern.compile("^\\d{2}[-\\s]?\\d{2}[-\\s]?\\d{2}$");
    
    /**
     * Validates if the provided sort code is valid.
     *
     * @param sortCode the sort code to validate
     * @return true if the sort code is valid, false otherwise
     */
    public boolean isValid(String sortCode) {
        if (sortCode == null || sortCode.isEmpty()) {
            return false;
        }
        
        return SORT_CODE_PATTERN.matcher(sortCode).matches();
    }
    
    /**
     * Normalizes a sort code by removing any separators.
     *
     * @param sortCode the sort code to normalize
     * @return the normalized sort code (6 digits without separators) or null if invalid
     */
    public String normalize(String sortCode) {
        if (!isValid(sortCode)) {
            return null;
        }
        
        return sortCode.replaceAll("[\\s-]", "");
    }
    
    /**
     * Formats a sort code with hyphens (XX-XX-XX).
     *
     * @param sortCode the sort code to format
     * @return the formatted sort code or null if invalid
     */
    public String format(String sortCode) {
        String normalized = normalize(sortCode);
        if (normalized == null) {
            return null;
        }
        
        return normalized.substring(0, 2) + "-" + 
               normalized.substring(2, 4) + "-" + 
               normalized.substring(4, 6);
    }
    
    /**
     * Extracts the bank identifier from a sort code (first 2 digits).
     *
     * @param sortCode the sort code
     * @return the bank identifier or null if the sort code is invalid
     */
    public String getBankIdentifier(String sortCode) {
        String normalized = normalize(sortCode);
        if (normalized == null) {
            return null;
        }
        
        return normalized.substring(0, 2);
    }
    
    /**
     * Extracts the branch identifier from a sort code (last 4 digits).
     *
     * @param sortCode the sort code
     * @return the branch identifier or null if the sort code is invalid
     */
    public String getBranchIdentifier(String sortCode) {
        String normalized = normalize(sortCode);
        if (normalized == null) {
            return null;
        }
        
        return normalized.substring(2, 6);
    }
}