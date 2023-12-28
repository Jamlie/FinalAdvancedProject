package edu.najah.cap.delete.internal.database;

public record SoftDeletedUsersModel(String username, String email, String password) implements Model {
}
