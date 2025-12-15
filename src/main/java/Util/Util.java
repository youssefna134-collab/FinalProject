package Util;

public class Util {
    /**
     * Converts each word of a string into title case.
     * @param str the string input
     * @return every word of the string in title case
     */
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split(" ");
        String titleCaseString = "";

        for (String word : words) {
            String titleCaseWord = word.substring(0, 1).toUpperCase()
                        + word.substring(1).toLowerCase();

            if (!titleCaseString.isEmpty()) {
                titleCaseString += " ";
            }

            titleCaseString += titleCaseWord;
        }

        return titleCaseString;
    }
}

