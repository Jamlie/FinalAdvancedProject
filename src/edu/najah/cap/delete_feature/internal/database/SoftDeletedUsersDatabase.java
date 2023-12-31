package edu.najah.cap.delete_feature.internal.database;

import edu.najah.cap.delete_feature.DatabaseType;
import edu.najah.cap.delete_feature.SQLValidation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoftDeletedUsersDatabase extends Database<SoftDeletedUsersModel> {
    private static SoftDeletedUsersDatabase instance;
    private Connection connection;
    private final String dbName;
    private boolean connected = false;
    private final DatabaseType databaseType;
    private static final Logger logger = Logger.getLogger(SoftDeletedUsersDatabase.class.getName());

    private SoftDeletedUsersDatabase(DatabaseType databaseType, String dbName) {
        this.databaseType = databaseType;
        this.dbName = dbName;
    }

    public static Database<SoftDeletedUsersModel> getInstance(DatabaseType type, String dbName) {
        if (instance == null) {
            synchronized (HardDeletedUsersDatabase.class) {
                if (instance == null) {
                    instance = new SoftDeletedUsersDatabase(type, dbName);
                    logger.log(Level.INFO, "SoftDeletedUsersDatabase instance created");
                }
            }
        }
        return instance;
    }

    @Override
    protected boolean isConnected() {
        return connected;
    }

    protected void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    protected Connection getConnection() {
        return this.connection;
    }

    @Override
    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() throws SQLException {
        connect(databaseType, dbName, logger);
    }

    @Override
    protected SQLException createTable() {
        try {
            connection
                    .createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS soft_deleted_users (" +
                            "username TEXT PRIMARY KEY," +
                            "email TEXT UNIQUE NOT NULL," +
                            "password TEXT NOT NULL" +
                            ");");
            logger.log(Level.INFO, "Table soft_deleted_users created");
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating table soft_deleted_users: {0}", e);
            return e;
        }
    }

    @Override
    public void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
            logger.log(Level.INFO, "Disconnected from database {0}", dbName);
            connected = false;
        }
    }

    @Override
    public void insert(SoftDeletedUsersModel model) throws SQLException {
        boolean isUsernameValid = SQLValidation.isStringValid(model.username());
        if (!isUsernameValid) {
            logger.log(Level.WARNING, "Username is not valid");
            return;
        }

        try {
            connection
                    .createStatement()
                    .executeUpdate("INSERT INTO soft_deleted_users (username, email, password) VALUES (" +
                            "'" + model.username() + "'," +
                            "'" + model.email() + "'," +
                            "'" + model.password() + "'" +
                            ");");
            logger.log(Level.INFO, "Inserted user {0} into soft_deleted_users", model.username());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting user {0} into soft_deleted_users: {1}", new Object[]{model.username(), e});
        }
    }
}
