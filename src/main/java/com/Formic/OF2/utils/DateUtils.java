package com.Formic.OF2.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getCurrentDateTime(String dateFormatMask) {
        // Define the date and time format
        String adjustedFormat = dateFormatMask.replace("mm", "MM").replace("hh", "HH").replace(":MM:",":mm:");
        String[] split = adjustedFormat.split(" ");

        // Define the date and time formatter using the adjusted format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(split[0]);

        // Get the current date and time from the system clock
        LocalDateTime now = LocalDateTime.now();

        // Format the current date and time
        return now.format(formatter);
    }
}
