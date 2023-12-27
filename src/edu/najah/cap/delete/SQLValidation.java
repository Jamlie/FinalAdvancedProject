package edu.najah.cap.delete;

public class SQLValidation {
    private SQLValidation() {}

    public static boolean isStringValid(String str) {
        return str != null && !str.isEmpty() && !isSQLInjection(str);
    }

    private static boolean isSQLInjection(String str) {
        return str.contains(";") || str.contains("--") || str.contains("/*") || str.contains("*/") || str.contains("'");
    }
}
