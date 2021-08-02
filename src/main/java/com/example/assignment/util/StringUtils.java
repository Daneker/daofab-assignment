package com.example.assignment.util;

/**
 * Util class for working with strings
 */
public class StringUtils {
    public static final String ID = "id";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String KEY_VALUE_DELIMITER = ":";

    public static String parseOrderBy(String text) {
        return text == null || text.isEmpty() ? null : text.split(KEY_VALUE_DELIMITER)[1];
    }

    public static String parseOrderParameter(String text) {
        return text == null || text.isEmpty() ? null : text.split(KEY_VALUE_DELIMITER)[0];
    }
}
