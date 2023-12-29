package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

import java.util.List;

public class UserActivitiesData {
    private  final IUserActivityService userActivityService;

    public UserActivitiesData(IUserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    public List<UserActivity> getUserActivities(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        return userActivityService.getUserActivity(userName);
    }
}
