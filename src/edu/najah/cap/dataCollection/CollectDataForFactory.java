package edu.najah.cap.dataCollection;

import edu.najah.cap.dataCollection.UserTypes.CollectForNewUser;
import edu.najah.cap.dataCollection.UserTypes.CollectForPremiumUser;
import edu.najah.cap.dataCollection.UserTypes.CollectForRegularUser;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public class CollectDataForFactory {

    private  final UsersData usersData;
    private  final String userName;
    public CollectDataForFactory(UsersData usersData) {
        this.usersData = usersData;
        this.userName = usersData.getUserName();
    }

    public CollectData getCollectionDataFor (){
        try{
            try {
                return switch (usersData.getUserProfileData().getUserProfile(userName).getUserType()) {
                    case REGULAR_USER -> new CollectForRegularUser(new UserData.Builder()
                            .setUserProfile(usersData.getUserProfileData().getUserProfile(userName))
                            .setPosts(usersData.getUserPostsData().getPosts(userName))
                            .setUserActivities(usersData.getUserActivitiesData().getUserActivities(userName))
                            .build());

                    case PREMIUM_USER -> new CollectForPremiumUser(new UserData.Builder()
                            .setUserProfile(usersData.getUserProfileData().getUserProfile(userName))
                            .setPosts(usersData.getUserPostsData().getPosts(userName))
                            .setUserActivities(usersData.getUserActivitiesData().getUserActivities(userName))
                            .setTransactions(usersData.getUserTransactionsData().getTransactions(userName))
                            .setBalance(usersData.getUserTransactionsData().getBalance(userName))
                            .build());

                    case NEW_USER -> new CollectForNewUser(new UserData.Builder()
                            .setUserProfile(usersData.getUserProfileData().getUserProfile(userName))
                            .build());

                    default -> throw new IllegalArgumentException("Invalid user type");
                };
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
                throw new RuntimeException(e);
            }
        }catch (NullPointerException e){
            throw new IllegalArgumentException("UserContext or UserProfile cannot be null");
        }
    }
}
