package edu.najah.cap.delete_feature.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;

import java.util.logging.Logger;

public class UserServiceDeleter implements IServiceDeleter {
    private final IUserService userService;
    private static Logger logger = Logger.getLogger(UserServiceDeleter.class.getName());

    public UserServiceDeleter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public synchronized void delete(String username) {
        try {
            userService.deleteUser(username);
            logger.info("Deleted user " + username);
        } catch (SystemBusyException sbe) {
            logger.warning("Warning: Failed to delete user " + username);
        } catch (NotFoundException | BadRequestException e) {
            logger.severe("Error: Failed to delete user " + username);
            throw new RuntimeException(e);
        }
    }
}
