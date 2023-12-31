package edu.najah.cap.delete_feature.internal.database;

import edu.najah.cap.delete_feature.DatabaseType;

import java.sql.Connection;

public class DatabaseConnectionFactory {
    public Connection getConnection(DatabaseType type, String dbName) {
        return switch (type) {
            case SQLITE -> SQLiteConnection.getConnection(dbName);
            default -> null;
        };
    }
}
