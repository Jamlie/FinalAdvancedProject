package edu.najah.cap.delete.internal.delete_service;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

import java.time.Instant;
import java.util.List;

public class UserActivityDeleter implements IServiceDeleter {
    private IUserActivityService userActivityService;

    public UserActivityDeleter(IUserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public synchronized void delete(String username) {
        List<UserActivity> userActivities;
        try {
            userActivities = userActivityService.getUserActivity(username);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        var userActivityIterator = userActivities.iterator();

        while (userActivityIterator.hasNext()) {
            try {
//                try {
//                    if (Instant.now().getEpochSecond() % 3 == 0) {
//                        Thread.sleep(1);
//                    }
//                } catch (Exception e) {}
                var userActivity = userActivityIterator.next();
                userActivityIterator.remove();
                userActivityService.removeUserActivity(username, userActivity.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
            }
        }
    }
}
