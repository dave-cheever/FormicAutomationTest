package com.Formic.OF2.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class FormatMask {

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
