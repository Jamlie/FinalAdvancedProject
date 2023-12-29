package edu.najah.cap.dataCollection;


import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import java.util.List;
public class DataCollectionFor {

    public CollectedData getCollectionDataFor (UserData userData){
        try{
            return switch (userData.getUserProfile().getUserType()) {
                case REGULAR_USER -> new CollectedForRegularUser(new UserData.Builder().setUserProfile(userData.getUserProfile()).setPosts(userData.getPosts()).setUserActivities(userData.getUserActivities()).build());
                case PREMIUM_USER -> new CollectedForPremiumUser(new UserData.Builder().setUserProfile(userData.getUserProfile()).setPosts(userData.getPosts()).setUserActivities(userData.getUserActivities()).setUserActivities(userData.getUserActivities()).setBalance(userData.getBalance()).build());
                case NEW_USER -> new CollectedForNewUser(new UserData.Builder().setUserProfile(userData.getUserProfile()).build());

                default -> throw new IllegalArgumentException("Invalid user type");
            };
        }catch (NullPointerException e){
            throw new IllegalArgumentException("UserContext or UserProfile cannot be null");
        }
    }
}
