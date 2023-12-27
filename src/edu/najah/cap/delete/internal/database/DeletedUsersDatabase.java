package edu.najah.cap.delete.internal.database;

import edu.najah.cap.delete.DatabaseType;
import edu.najah.cap.delete.SQLValidation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DeletedUsersDatabase extends Database<DeletedUsersModel> {
    private static DeletedUsersDatabase instance;
    private Connection connection;
    private final String dbName;
    private boolean isConnected = false;
    private DatabaseType databaseType;

    private DeletedUsersDatabase(DatabaseType databaseType, String dbName) {
        this.databaseType = databaseType;
        this.dbName = dbName;
    }

    public static Database<DeletedUsersModel> getInstance(DatabaseType type, String dbName) {
        if (instance == null) {
            synchronized (DeletedUsersDatabase.class) {
                if (instance == null) {
                    instance = new DeletedUsersDatabase(type, dbName);
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
                    .executeUpdate("CREATE TABLE IF NOT EXISTS deleted_users (username TEXT PRIMARY KEY)");
            return null;
        } catch (SQLException e) {
            return e;
        }
    }

    @Override
    final public void disconnect() throws SQLException {
        if (isConnected) {
            connection.close();
            isConnected = false;
        }
    }

    @Override
    public void insert(DeletedUsersModel model) throws SQLException {
        boolean isUsernameValid = SQLValidation.isStringValid(model.username());
        if (!isUsernameValid) {
            throw new SQLException("Username is not valid");
        }

        var statement = connection.prepareStatement("INSERT INTO deleted_users (username) VALUES (?)");
        statement.setString(1, model.username());
        statement.executeUpdate();
    }
}
