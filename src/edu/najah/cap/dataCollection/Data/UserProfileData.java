package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;

public class UserProfileData {
    private static IUserService userService;

    public UserProfileData(IUserService userService) {
        UserProfileData.userService = userService;
    }
    public UserProfile getUserProfile(String userName) throws SystemBusyException, NotFoundException, BadRequestException {
        try{
            return userService.getUser(userName);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}
