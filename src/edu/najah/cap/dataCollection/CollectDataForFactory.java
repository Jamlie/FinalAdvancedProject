package edu.najah.cap.dataCollection;

import edu.najah.cap.dataCollection.UserTypes.CollectDataForNewUser;
import edu.najah.cap.dataCollection.UserTypes.CollectDataForPremiumUser;
import edu.najah.cap.dataCollection.UserTypes.CollectDataForRegularUser;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CollectDataForFactory {
    private static final Logger collectDataForFactoryLogger = Logger.getLogger(CollectDataForFactory.class.getName());
    private  final UsersData usersData;
    private  final String userName;
    public CollectDataForFactory(UsersData usersData) {
        this.usersData = usersData;
        this.userName = usersData.getUserName();
    }

    public CollectData getCollectionDataFor (){
        try {
            try {
                return switch (usersData.getUsersProfileData().getUserProfile(userName).getUserType()) {
                    case REGULAR_USER -> {
                        CollectDataForRegularUser collectDataForRegularUser = new CollectDataForRegularUser(new UserData.Builder()
                                .setUserProfile(usersData.getUsersProfileData().getUserProfile(userName))
                                .setPosts(usersData.getUsersPostsData().getPosts(userName))
                                .setUserActivities(usersData.getUsersActivitiesData().getUserActivities(userName))
                                .build());

                        collectDataForFactoryLogger.log(Level.INFO, "Create CollectDataForRegularUser instance for user: {0}", userName);
                        yield collectDataForRegularUser;
                    }

                    case PREMIUM_USER -> {
                        CollectDataForPremiumUser collectDataForPremiumUser = new CollectDataForPremiumUser(new UserData.Builder()
                                .setUserProfile(usersData.getUsersProfileData().getUserProfile(userName))
                                .setPosts(usersData.getUsersPostsData().getPosts(userName))
                                .setUserActivities(usersData.getUsersActivitiesData().getUserActivities(userName))
                                .setTransactions(usersData.getUsersTransactionsData().getTransactions(userName))
                                .setBalance(usersData.getUsersTransactionsData().getBalance(userName))
                                .build());

                        collectDataForFactoryLogger.log(Level.INFO, "Create CollectDataForPremiumUser instance for user: {0}", userName);
                        yield collectDataForPremiumUser;
                    }

                    case NEW_USER -> {
                        CollectDataForNewUser collectDataForNewUser = new CollectDataForNewUser(new UserData.Builder()
                                .setUserProfile(usersData.getUsersProfileData().getUserProfile(userName))
                                .build());

                        collectDataForFactoryLogger.log(Level.INFO, "Create CollectDataForNewUser instance for user: {0}", userName);
                        yield collectDataForNewUser;
                    }

                    default -> throw new IllegalArgumentException("Invalid user type");
                };
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
                collectDataForFactoryLogger.log(Level.WARNING, "Error occurred while getting user data for user: {0}", new Object[]{userName, e});
                throw new RuntimeException(e);
            }
        } catch (NullPointerException e) {
            collectDataForFactoryLogger.log(Level.SEVERE, "UserContext or UserProfile cannot be null for user: {0}", userName);
            throw new IllegalArgumentException("UserContext or UserProfile cannot be null");
        }
    }
}
