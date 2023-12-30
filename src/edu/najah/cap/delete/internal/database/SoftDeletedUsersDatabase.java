package edu.najah.cap.delete.internal.database;

import edu.najah.cap.delete.DatabaseType;
import edu.najah.cap.delete.SQLValidation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class SoftDeletedUsersDatabase extends Database<SoftDeletedUsersModel> {
    private static SoftDeletedUsersDatabase instance;
    private Connection connection;
    private final String dbName;
    private boolean isConnected = false;
    private final DatabaseType databaseType;

    private SoftDeletedUsersDatabase(DatabaseType databaseType, String dbName) {
        this.databaseType = databaseType;
        this.dbName = dbName;
    }

    public static Database<SoftDeletedUsersModel> getInstance(DatabaseType type, String dbName) {
        if (instance == null) {
            synchronized (HardDeletedUsersDatabase.class) {
                if (instance == null) {
                    instance = new SoftDeletedUsersDatabase(type, dbName);
                }
            }
        }
        return instance;
    }

    @Override
    public void connect() throws SQLException {
        if (!isConnected) {
            connection = this.connect(dbName);
            if (Objects.isNull(connection)) {
                throw new SQLException("Connection is null");
            }
            isConnected = true;
            var err = createTable();
            if (Objects.nonNull(err)) {
                throw err;
            }
        }
    }

    private Connection connect(String dbName) {
        return DatabaseConnection.getConnection(databaseType, dbName);
    }

    @Override
    protected SQLException createTable() throws SQLException {
        try {
            connection
                    .createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS soft_deleted_users (" +
                            "username TEXT PRIMARY KEY," +
                            "email TEXT UNIQUE NOT NULL," +
                            "password TEXT NOT NULL" +
                            ");");
            return null;
        } catch (SQLException e) {
            return e;
        }
    }

    @Override
    public void disconnect() throws SQLException {
        if (isConnected) {
            connection.close();
            isConnected = false;
        }
    }

    @Override
    public void insert(SoftDeletedUsersModel model) throws SQLException {
        boolean isUsernameValid = SQLValidation.isStringValid(model.username());
        if (!isUsernameValid) {
            throw new SQLException("Username is not valid");
        }

        try {
            connection
                    .createStatement()
                    .executeUpdate("INSERT INTO soft_deleted_users (username, email, password) VALUES (" +
                            "'" + model.username() + "'," +
                            "'" + model.email() + "'," +
                            "'" + model.password() + "'" +
                            ");");
        } catch (SQLException e) {
            throw e;
        }
    }
}
