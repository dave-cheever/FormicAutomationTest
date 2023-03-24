package Helpers;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
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
        return "";
    }
}

