package edu.najah.cap.dataCollection;


import edu.najah.cap.dataCollection.Data.UserActivitiesData;
import edu.najah.cap.dataCollection.Data.UserPostsData;
import edu.najah.cap.dataCollection.Data.UserProfileData;
import edu.najah.cap.dataCollection.Data.UserTransactionsData;

public class UsersData {
    private final UserProfileData userProfileData;
    private final UserPostsData userPostsData ;
    private final UserActivitiesData userActivitiesData ;
    private final UserTransactionsData userTransactionsData ;
    private final String userName ;

    public UsersData(String userName,UserProfileData userProfileData, UserPostsData userPostsData, UserActivitiesData userActivitiesData, UserTransactionsData userTransactionsData) {
        this.userProfileData = userProfileData;
        this.userPostsData = userPostsData;
        this.userActivitiesData = userActivitiesData;
        this.userTransactionsData = userTransactionsData;
        this.userName = userName;
    }


    public static class Builder {
        private UserProfileData userProfileData;
        private UserPostsData userPostsData ;
        private UserActivitiesData userActivitiesData ;
        private UserTransactionsData userTransactionsData ;
        private String userName ;

        public Builder setUserProfileData(UserProfileData userProfileData) {
            this.userProfileData = userProfileData;
            return this;

        }

        public Builder setUserPostsData(UserPostsData userPostsData) {
            this.userPostsData = userPostsData;
            return this;
        }

        public Builder setUserActivitiesData(UserActivitiesData userActivitiesData) {
            this.userActivitiesData = userActivitiesData;
            return this;
        }

        public Builder setUserTransactionsData(UserTransactionsData userTransactionsData) {
            this.userTransactionsData = userTransactionsData;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UsersData build() {
            return new UsersData(userName,userProfileData,userPostsData,userActivitiesData,userTransactionsData);
        }
    }

    public UserProfileData getUserProfileData() {
        return userProfileData;
    }

    public UserPostsData getUserPostsData() {
        return userPostsData;
    }

    public UserActivitiesData getUserActivitiesData() {
        return userActivitiesData;
    }

    public UserTransactionsData getUserTransactionsData() {
        return userTransactionsData;
    }

    public String getUserName() {
        return userName;
    }
}
