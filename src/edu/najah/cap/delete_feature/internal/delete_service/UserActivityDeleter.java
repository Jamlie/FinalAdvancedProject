package edu.najah.cap.delete_feature.internal.delete_service;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

public class UserActivityDeleter implements IServiceDeleter {
    private final IUserActivityService userActivityService;
    private static Logger logger = Logger.getLogger(UserActivityDeleter.class.getName());

    public UserActivityDeleter(IUserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public synchronized void delete(String username) {
        List<UserActivity> userActivities;
        try {
            userActivities = userActivityService.getUserActivity(username);
        } catch (SystemBusyException ignored) {
            logger.warning("Warning: Failed to get user activities for user " + username);
            return;
        } catch (NotFoundException | BadRequestException e) {
            logger.severe("Error: Failed to get user activities for user " + username);
            throw new RuntimeException(e);
        }

        var userActivityIterator = userActivities.iterator();

        while (userActivityIterator.hasNext()) {
            try {
                try {
                    if (Instant.now().getEpochSecond() % 3 == 0) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ignored) {}
                var userActivity = userActivityIterator.next();
                userActivityIterator.remove();
                userActivityService.removeUserActivity(username, userActivity.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
                logger.warning("Warning: Failed to delete user activity for user " + username);
            }
        }
    }
}
