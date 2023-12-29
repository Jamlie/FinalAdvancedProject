package edu.najah.cap.dataCollection;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.util.List;

public class UserData {
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

    public double getBalance() {
        return balance;
    }
}
