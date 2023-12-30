package edu.najah.cap.delete.internal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URI_PREFIX = "jdbc:sqlite:";

    public static Connection getConnection(String dbName) {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URI_PREFIX + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
