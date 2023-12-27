package edu.najah.cap.delete.internal.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Database<T> {
    public abstract void connect() throws SQLException;
    protected abstract SQLException createTable() throws SQLException;
    public abstract void disconnect() throws SQLException;
    public abstract void insert(T model) throws SQLException;
}
