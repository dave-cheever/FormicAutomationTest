package com.Formic.OF2.utils;
import java.util.regex.Pattern;

public class FormatRegex {
    public static String generateFormattedString(String regex) {
        if (regex.equals("^[a-zA-Z]{1,2}[0-9]{1,2}[ ][0-9]{1}[a-zA-Z]{2}$")) {
            return "AB6 1PD";
        }
        if (regex.equals("^[a-zA-Z]+$")) {
            return "abcdEF";
        }
        if (regex.equals("^[0][ 0123456789]+$")) {
            return "0123456789";
        }
        if (regex.equals("^[a-zA-Z0-9]+$")) {
            return "Abc123";
        }
        if (regex.equals("^[0-9]{2}[-][0-9]{2}[-][0-9]{2}$")) {
            return "12-34-56";
        }
        if (regex.equals("^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$")) {
            return "abc@def.com";
        }
        if (regex.equals("^[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}$")) {
            return "1234 5678 9012 3456";
        }
        if(regex.equals("^[a-zA-Z0-9]{1,5}$")){
            return "123Wa";
        }
        if(regex.equals("^[a-zA-Z0-9]{1,10}$")){
            return "2123Wa1123";
        }
        return "";
    }

    public enum RegexType {
        ONLY_LETTERS,
        ONLY_NUMBERS,
        ALPHANUMERIC,
        OTHER
    }

    public static RegexType getRegexType(String formatRegex) {
        String testLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String testNumbers = "0123456789";

        Pattern pattern = Pattern.compile(formatRegex);
        boolean matchesLetters = false;
        boolean matchesNumbers = false;

        for (char c : testLetters.toCharArray()) {
            if (pattern.matcher(Character.toString(c)).matches()) {
                matchesLetters = true;
                break;
            }
        }

        for (char c : testNumbers.toCharArray()) {
            if (pattern.matcher(Character.toString(c)).matches()) {
                matchesNumbers = true;
                break;
            }
        }

        if (matchesLetters && matchesNumbers) {
            return RegexType.ALPHANUMERIC;
        } else if (matchesLetters) {
            return RegexType.ONLY_LETTERS;
        } else if (matchesNumbers) {
            return RegexType.ONLY_NUMBERS;
        } else {
            return RegexType.OTHER;
        }
    }
}

