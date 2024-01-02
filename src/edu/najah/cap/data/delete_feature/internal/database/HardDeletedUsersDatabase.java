package edu.najah.cap.data.delete_feature.internal.database;

import edu.najah.cap.data.delete_feature.DatabaseType;
import edu.najah.cap.data.delete_feature.SQLValidation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HardDeletedUsersDatabase extends Database<HardDeletedUsersModel> {
    private static HardDeletedUsersDatabase instance;
    private Connection connection;
    private final String dbName;
    private boolean connected = false;
    private final DatabaseType databaseType;
    private static final Logger logger = Logger.getLogger(HardDeletedUsersDatabase.class.getName());


    private HardDeletedUsersDatabase(DatabaseType databaseType, String dbName) {
        this.databaseType = databaseType;
        this.dbName = dbName;
    }

    public static Database<HardDeletedUsersModel> getInstance(DatabaseType type, String dbName) {
        if (instance == null) {
            synchronized (HardDeletedUsersDatabase.class) {
                if (instance == null) {
                    instance = new HardDeletedUsersDatabase(type, dbName);
                    logger.log(Level.INFO, "HardDeletedUsersDatabase instance created");
                }
            }
        }
        return instance;
    }

    @Override
    protected boolean isConnected() {
        return connected;
    }

    @Override
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
                    .executeUpdate("CREATE TABLE IF NOT EXISTS hard_deleted_users (username TEXT PRIMARY KEY)");
            logger.log(Level.INFO, "Table deleted_users created");
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating table deleted_users: {0}", e);
            return e;
        }
    }

    @Override
    final public void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
            logger.log(Level.INFO, "Disconnected from database {0}", dbName);
            setConnected(false);
        }
    }

    @Override
    public void insert(HardDeletedUsersModel model) throws SQLException {
        boolean isUsernameValid = SQLValidation.isStringValid(model.username());
        if (!isUsernameValid) {
            logger.log(Level.WARNING, "Username is not valid");
            return;
        }

        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO hard_deleted_users (username) VALUES ('" + model.username() + "')");
        logger.log(Level.INFO, "Inserted {0} into deleted_users", model.username());
    }
}
