package com.Formic.OF2.utils;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FormatMask {

    public static String convertFormatMask(String formatMask) {
        return formatMask
                .replace("dd/mm/yy", "dd/MM/yy")
                .replace("dd/mm/yyyy", "dd/MM/yyyy")
                .replace("dd mmmm yyyy", "dd MMMM yyyy")
                .replace("mmmm dd yyyy", "MMMM dd yyyy")
                .replace("dd/mm/yy hh:mm:ss", "dd/MM/yy HH:mm:ss")
                .replace("dd/mm/yyyy hh:mm:ss", "dd/MM/yyyy HH:mm:ss")
                .replace("dd/mm", "dd/MM")
                .replace("mm/dd/yy", "MM/dd/yy")
                .replace("mm/dd/yyyy", "MM/dd/yyyy")
                .replace("mm/dd/yy hh:mm:ss", "MM/dd/yy HH:mm:ss")
                .replace("mm/dd/yyyy hh:mm:ss", "MM/dd/yyyy HH:mm:ss")
                .replace("mm/yy", "MM/yy")
                .replace("yy/mm/dd", "yy/MM/dd")
                .replace("yyyy/mm/dd", "yyyy/MM/dd")
                .replace("yy/mm/dd hh:mm:ss", "yy/MM/dd HH:mm:ss")
                .replace("yyyy/mm/dd hh:mm:ss", "yyyy/MM/dd HH:mm:ss")
                .replace("yy/mm", "yy/MM")
                .replace("hh:mm:ss", "HH:mm:ss")
                .replace("hh:mm", "HH:mm")
                .replace("mm:ss", "mm:ss")
                .replace("DD-MM-YYY", "dd-MM-yyyy");
    }

    public static String getRandomDate(String formatMask) {
        // Create a date format based on the given format mask
        String convertedFormatMask = convertFormatMask(formatMask);
        SimpleDateFormat dateFormat = new SimpleDateFormat(convertedFormatMask);

        // Generate random values for year, month, day, hour, minute, second
        Random random = new Random();
        int year = random.nextInt(50) + 1970; // Random year between 1970 and 2019
        int month = random.nextInt(12); // Month between 0 and 11 (January to December)
        int day = random.nextInt(31) + 1; // Day between 1 and 31
        int hour = random.nextInt(24); // Hour between 0 and 23
        int minute = random.nextInt(60); // Minute between 0 and 59
        int second = random.nextInt(60); // Second between 0 and 59

        // Adjust the day if necessary based on the month and year
        Calendar calendar = new GregorianCalendar(year, month, day);
        day = Math.min(day, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, day);

        // Set random hour, minute, and second
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        // Return the formatted date
        return dateFormat.format(calendar.getTime());
    }

    public static String formatDateTime(String formatMask) {
        Map<String, String> formatMap = new HashMap<>();
        formatMap.put("dd/mm/yy", "dd/MM/yy");
        formatMap.put("dd/mm/yyyy", "dd/MM/yyyy");
        formatMap.put("dd mmmm yyyy", "dd MMMM yyyy");
        formatMap.put("mmmm dd yyyy", "MMMM dd yyyy");
        formatMap.put("dd/mm/yy hh:mm:ss", "dd/MM/yy HH:mm:ss");
        formatMap.put("dd/mm/yyyy hh:mm:ss", "dd/MM/yyyy HH:mm:ss");
        formatMap.put("dd/mm", "dd/MM");
        formatMap.put("mm/dd/yy", "MM/dd/yy");
        formatMap.put("mm/dd/yyyy", "MM/dd/yyyy");
        formatMap.put("mm/dd/yy hh:mm:ss", "MM/dd/yy HH:mm:ss");
        formatMap.put("mm/dd/yyyy hh:mm:ss", "MM/dd/yyyy HH:mm:ss");
        formatMap.put("mm/yy", "MM/yy");
        formatMap.put("yy/mm/dd", "yy/MM/dd");
        formatMap.put("yyyy/mm/dd", "yyyy/MM/dd");
        formatMap.put("yy/mm/dd hh:mm:ss", "yy/MM/dd HH:mm:ss");
        formatMap.put("yyyy/mm/dd hh:mm:ss", "yyyy/MM/dd HH:mm:ss");
        formatMap.put("yy/mm", "yy/MM");
        formatMap.put("hh:mm:ss", "HH:mm:ss");
        formatMap.put("hh:mm", "HH:mm");
        formatMap.put("mm:ss", "mm:ss");
        formatMap.put("DD-MM-YYY", "dd-MM-yyyy");

        String javaFormat = formatMap.get(formatMask);
        if (javaFormat == null) {
//            throw new IllegalArgumentException("Invalid format mask: " + formatMask);
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(javaFormat);
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Invalid date time format: " + formatMask, e);
            return null;
        }
    }

}
