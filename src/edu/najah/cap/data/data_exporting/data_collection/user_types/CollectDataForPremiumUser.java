package edu.najah.cap.data.data_exporting.data_collection.user_types;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.data.data_exporting.data_collection.UserData;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CollectDataForPremiumUser implements CollectData {
    private static final Logger collectDataForPremiumUserLogger = Logger.getLogger(CollectDataForPremiumUser.class.getName());
    private final UserData userData;
    private final UserProfile userProfile;
    private final List<Post> posts;
    private final List<UserActivity> userActivities;
    private final List<Transaction> transactions;
    private final double balance;

    public CollectDataForPremiumUser(UserData userData){
        this.userData = userData;
        this.userProfile = userData.getUserProfile();
        this.posts = userData.getPosts();
        this.userActivities = userData.getUserActivities();
        this.transactions = userData.getTransactions();
        this.balance = userData.getBalance();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    @Override
    public UserData collect() {
        try {
            collectDataForPremiumUserLogger.log(Level.INFO, "Collecting data for premium user: {0}", userProfile.getUserName());
            return userData;
        } catch (Exception e) {
            collectDataForPremiumUserLogger.log(Level.SEVERE, "Unexpected error occurred while collecting data for premium user: {0}", new Object[]{userProfile.getUserName(), e});
            return null;
        }
    }
}
