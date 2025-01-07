package com.shoping.mavenproject7.helper;

public class Helper {

    public static String getWord(String Disc) {
        // Check if the input is null or empty
        if (Disc == null || Disc.trim().isEmpty()) {
            return "";
        }

        // Split the input string into words
        String[] str = Disc.trim().split("\\s+");

        // If the number of words is greater than 10, truncate and append "..."
        if (str.length > 10) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                result.append(str[i]).append(" ");
            }
            result.append("..."); // Add ellipsis after the truncated words
            return result.toString().trim();
        } else {
            // If the number of words is less than or equal to 10, return the original string with "..."
            return Disc + " ...";
        }
    }
}
