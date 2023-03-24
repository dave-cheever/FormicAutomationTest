package Helpers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputLimitExtractor {
    public static int extractInputLimit(String formatRegex) {
        Pattern pattern = Pattern.compile("\\{\\d+,\\d+\\}");
        Matcher matcher = pattern.matcher(formatRegex);

        if (matcher.find()) {
            String limits = matcher.group();
            String[] minMax = limits.substring(1, limits.length() - 1).split(",");

            try {
                int maxLimit = Integer.parseInt(minMax[1]);
                return maxLimit;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input limit in regex: " + formatRegex, e);
            }
        } else {
            throw new IllegalArgumentException("Input limit not found in regex: " + formatRegex);
        }
    }
}
