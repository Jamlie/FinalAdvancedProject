package edu.najah.cap.data.data_exporting.data_collection;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserData {
    private static final Logger userDataLogger = Logger.getLogger(UserData.class.getName() + "Logger");
    private final  UserProfile userProfile;
    private final  List<Post> posts;
    private final  List<UserActivity> userActivities;
    private final  List<Transaction> transactions;
    private final double balance;


    public UserData(UserProfile userProfile, List<UserActivity> userActivities, List<Post> posts, List<Transaction> transactions, double balance) {
        this.userProfile = userProfile;
        this.posts = posts;
        this.userActivities = userActivities ;
        this.transactions = transactions;
        this.balance = balance;

        userDataLogger.log(Level.INFO, "Create UserData object", userProfile.getUserName());
    }

    public static class Builder {
        private  UserProfile userProfile = null;
        private  List<Post> posts = null;
        private  List<UserActivity> userActivities = null;
        private  List<Transaction> transactions = null;
        private  double balance = -1;


        public Builder setUserProfile(UserProfile userProfile) {
            this.userProfile = userProfile;
            return this;
        }

        public Builder setPosts(List<Post> posts ) {
            this.posts = posts;
            return this;
        }

        public Builder setUserActivities(List<UserActivity> userActivities) {
            this.userActivities = userActivities;
            return this;
        }

        public Builder setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public UserData build() {
            return new UserData(userProfile,  userActivities,  posts,  transactions, balance);
        }
    }



    public UserProfile getUserProfile() {
        userDataLogger.log(Level.INFO, "Getting user profile for user: {0}", userProfile.getUserName());
        return userProfile;
    }

    public List<Post> getPosts() {
        userDataLogger.log(Level.INFO, "Getting posts for user: {0}", userProfile.getUserName());
        return posts;
    }

    public List<UserActivity> getUserActivities() {
        userDataLogger.log(Level.INFO, "Getting user activities for user: {0}", userProfile.getUserName());
        return userActivities;
    }

    public List<Transaction> getTransactions() {
        userDataLogger.log(Level.INFO, "Getting transactions for user: {0}", userProfile.getUserName());
        return transactions;
    }

    public double getBalance() {
        userDataLogger.log(Level.INFO, "Getting balance for user: {0}", userProfile.getUserName());
        return balance;
    }
}
