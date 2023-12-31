package edu.najah.cap.delete_feature.internal.database;

public record SoftDeletedUsersModel(String username, String email, String password) implements Model {
}
