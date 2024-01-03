package edu.najah.cap.data.delete_feature;

public class SQLValidation {
    private SQLValidation() {}

    public static boolean isStringValid(String str) {
        return str != null && !str.isEmpty() && !isSQLInjection(str);
    }

    public static boolean isStringEmail(String email) {
        return email != null && email.contains("@") && !isSQLInjection(email);
    }


    private static boolean isSQLInjection(String str) {
        return str.contains(";") || str.contains("--") || str.contains("/*") || str.contains("*/") || str.contains("'");
    }
}
