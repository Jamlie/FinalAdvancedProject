package edu.najah.cap.data.helpers;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserType;

public class UserTypeFetcher {
    private static IUserService userService;

    public UserTypeFetcher(IUserService userService) {
        UserTypeFetcher.userService = userService;
    }

    public static UserType getUserType(String username) {
        try {
            return userService.getUser(username).getUserType();
        } catch (NotFoundException | SystemBusyException | BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
