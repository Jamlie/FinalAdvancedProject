package edu.najah.cap.data.delete_feature.internal.database;

import edu.najah.cap.data.delete_feature.DatabaseType;

import java.sql.Connection;

public class DatabaseConnection {
    public static Connection getConnection(DatabaseType type, String dbName) {
        return new DatabaseConnectionFactory()
                .getConnection(type, dbName);
    }
}
