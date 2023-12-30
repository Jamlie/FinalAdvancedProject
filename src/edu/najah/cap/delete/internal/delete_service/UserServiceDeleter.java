package edu.najah.cap.delete.internal.delete_service;

import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;

import java.time.Instant;

public class UserServiceDeleter implements IServiceDeleter {
    private final IUserService userService;

    public UserServiceDeleter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public synchronized void delete(String username) {
        try {
            userService.deleteUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
