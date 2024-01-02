package edu.najah.cap.data.all_users_data;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ActivityServiceData {

    private static final Logger userActivitiesDataLogger = Logger.getLogger(ActivityServiceData.class.getName());
    private static IUserActivityService userActivityService;

    public ActivityServiceData(IUserActivityService userActivityService) {
        ActivityServiceData.userActivityService = userActivityService;
    }

    public  List<UserActivity> getUserActivities(String userId) throws SystemBusyException, BadRequestException, NotFoundException {
        try {
            userActivitiesDataLogger.log(Level.INFO, "Fetching user activities for userId: {0}", userId);
            return userActivityService.getUserActivity(userId);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            userActivitiesDataLogger.log(Level.SEVERE, "Error fetching user activities for userId: {0}", new Object[]{userId, e});
            return null;
        } catch (Exception e) {
            userActivitiesDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching user activities for userId: {0}", new Object[]{userId, e});
            return null;
        }
    }
}
