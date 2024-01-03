package edu.najah.cap.data.delete_feature.internal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URI_PREFIX = "jdbc:sqlite:";
    private static final Logger logger = Logger.getLogger(SQLiteConnection.class.getName());

    public static Connection getConnection(String dbName) {
        try {
            Class.forName(DRIVER);
            logger.log(Level.INFO, "Connecting to database: {0}", dbName);
            return DriverManager.getConnection(URI_PREFIX + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to database {0}: {1}", new Object[]{dbName, e});
            throw new RuntimeException(e);
        }
    }
}
