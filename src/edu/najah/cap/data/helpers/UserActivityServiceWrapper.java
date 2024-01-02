package edu.najah.cap.data.helpers;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.Util;


public class UserActivityServiceWrapper extends UserActivityService {
    private IUserActivityService userActivityService;

    public UserActivityServiceWrapper() {
        super();
    }

    public UserActivityServiceWrapper(IUserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }


    @Override
    public synchronized void removeUserActivity(String userId, String id) throws SystemBusyException, BadRequestException, NotFoundException {
        Util.validateUserName(userId);
        var userActivities = userActivityService.getUserActivity(userId);

        if (userActivities != null) {
            var userActivity = userActivities
                    .stream()
                    .filter(activity -> activity.getId().equals(id))
                    .findFirst();

            userActivities.removeIf(activity -> activity.getId().equals(id));
            userActivity.ifPresent(userActivityService::addUserActivity);
        }
    }
}
