package edu.najah.cap.dataCollection;


import edu.najah.cap.dataCollection.Data.UserActivitiesData;
import edu.najah.cap.dataCollection.Data.UserPostsData;
import edu.najah.cap.dataCollection.Data.UserProfileData;
import edu.najah.cap.dataCollection.Data.UserTransactionsData;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersData {
    private static final Logger userDataLogger = Logger.getLogger(UsersData.class.getName() + "Logger");
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
        userDataLogger.log(Level.INFO, "Create UsersData object", userName);
    }


    public static class Builder {
        private UserProfileData userProfileData;
        private UserPostsData userPostsData ;
        private UserActivitiesData userActivitiesData ;
        private UserTransactionsData userTransactionsData ;
        private String userName ;

        public Builder setUsersProfileData(UserProfileData userProfileData) {
            this.userProfileData = userProfileData;
            return this;

        }

        public Builder setUsersPostsData(UserPostsData userPostsData) {
            this.userPostsData = userPostsData;
            return this;
        }

        public Builder setUsersActivitiesData(UserActivitiesData userActivitiesData) {
            this.userActivitiesData = userActivitiesData;
            return this;
        }

        public Builder setUsersTransactionsData(UserTransactionsData userTransactionsData) {
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

    public UserProfileData getUsersProfileData() {
        userDataLogger.log(Level.INFO, "Getting users profile data", userName);
        return userProfileData;
    }

    public UserPostsData getUsersPostsData() {
        userDataLogger.log(Level.INFO, "Getting users posts data", userName);
        return userPostsData;
    }

    public UserActivitiesData getUsersActivitiesData() {
        userDataLogger.log(Level.INFO, "Getting users activities", userName);
        return userActivitiesData;
    }

    public UserTransactionsData getUsersTransactionsData() {
        userDataLogger.log(Level.INFO, "Getting users transactions", userName);
        return userTransactionsData;
    }

    public String getUserName() {
        return userName;
    }
}
