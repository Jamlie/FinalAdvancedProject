package edu.najah.cap.data.AllUsersData;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileServiceData {
    private static final Logger userProfileDataLogger = Logger.getLogger(ProfileServiceData.class.getName());
    private static IUserService userService;

    public ProfileServiceData(IUserService userService) {
        ProfileServiceData.userService = userService;
    }
    public UserProfile getUserProfile(String userName) throws SystemBusyException, NotFoundException, BadRequestException {
        try{
            userProfileDataLogger.log(Level.INFO, "Fetching user profile for userName: {0}", userName);
            return userService.getUser(userName);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            userProfileDataLogger.log(Level.SEVERE, "Error fetching user profile for userName: {0}", new Object[]{userName, e});
            return null;
        } catch (Exception e) {
            userProfileDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching user profile for userName: {0}", new Object[]{userName, e});
            return null;
        }
    }
}
