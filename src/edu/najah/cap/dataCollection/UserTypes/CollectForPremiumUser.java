package edu.najah.cap.dataCollection.UserTypes;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.UserData;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.util.List;

public class CollectForPremiumUser implements CollectData {

    private final UserData userData;
    private final UserProfile userProfile;
    private final List<Post> posts;
    private final List<UserActivity> userActivities;
    private final List<Transaction> transactions;
    private final double balance;

    public CollectForPremiumUser(UserData userData){
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
    public UserData collect() { return userData; }
}
