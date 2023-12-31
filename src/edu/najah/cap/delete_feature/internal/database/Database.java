package edu.najah.cap.delete_feature.internal.database;

import edu.najah.cap.delete_feature.DatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Database<T extends Model> {
    public abstract void connect() throws SQLException;
    protected abstract SQLException createTable() throws SQLException;
    public abstract void disconnect() throws SQLException;
    public abstract void insert(T model) throws SQLException;

    protected abstract boolean isConnected();
    protected abstract void setConnected(boolean connected);
    protected abstract Connection getConnection();
    protected abstract void setConnection(Connection connection);

    protected void connect(DatabaseType databaseType, String dbName, Logger logger) throws SQLException {
        if (!isConnected()) {
            setConnection(connect(databaseType, dbName));
            if (Objects.isNull(getConnection())) {
                logger.log(Level.SEVERE, "Error connecting to database {0}", dbName);
                throw new SQLException("Error connecting to database " + dbName);
            }
            setConnected(true);
            var err = createTable();
            if (Objects.nonNull(err)) {
                logger.log(Level.SEVERE, "Error creating table: {0}", err);
                throw err;
            }
            logger.log(Level.INFO, "Connected to database {0}", dbName);
        }
    }

    final protected Connection connect(DatabaseType databaseType, String dbName) {
        return DatabaseConnection.getConnection(databaseType, dbName);
    }
}
