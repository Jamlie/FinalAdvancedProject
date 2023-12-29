package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;

public class UserProfileData {
    private final IUserService userService;

    public UserProfileData(IUserService userService) {
        this.userService = userService;
    }
    public UserProfile getUserProfile(String userName) throws SystemBusyException, NotFoundException, BadRequestException {
        return userService.getUser(userName);
    }
}
