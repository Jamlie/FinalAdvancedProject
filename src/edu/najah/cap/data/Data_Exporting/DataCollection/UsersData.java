package edu.najah.cap.data.Data_Exporting.DataCollection;


import edu.najah.cap.data.AllUsersData.ActivityServiceData;
import edu.najah.cap.data.AllUsersData.PostServiceData;
import edu.najah.cap.data.AllUsersData.ProfileServiceData;
import edu.najah.cap.data.AllUsersData.TransactionsServiceData;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersData {
    private static final Logger userDataLogger = Logger.getLogger(UsersData.class.getName() + "Logger");
    private final ProfileServiceData profileServiceData;
    private final PostServiceData postServiceData;
    private final ActivityServiceData activityServiceData;
    private final TransactionsServiceData transactionsServiceData;
    private final String userName ;

    public UsersData(String userName, ProfileServiceData profileServiceData, PostServiceData postServiceData, ActivityServiceData activityServiceData, TransactionsServiceData transactionsServiceData) {
        this.profileServiceData = profileServiceData;
        this.postServiceData = postServiceData;
        this.activityServiceData = activityServiceData;
        this.transactionsServiceData = transactionsServiceData;
        this.userName = userName;
        userDataLogger.log(Level.INFO, "Create UsersData object", userName);
    }


    public static class Builder {
        private ProfileServiceData profileServiceData;
        private PostServiceData postServiceData;
        private ActivityServiceData activityServiceData;
        private TransactionsServiceData transactionsServiceData;
        private String userName ;

        public Builder setUsersProfileData(ProfileServiceData profileServiceData) {
            this.profileServiceData = profileServiceData;
            return this;

        }

        public Builder setUsersPostsData(PostServiceData postServiceData) {
            this.postServiceData = postServiceData;
            return this;
        }

        public Builder setUsersActivitiesData(ActivityServiceData activityServiceData) {
            this.activityServiceData = activityServiceData;
            return this;
        }

        public Builder setUsersTransactionsData(TransactionsServiceData transactionsServiceData) {
            this.transactionsServiceData = transactionsServiceData;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UsersData build() {
            return new UsersData(userName, profileServiceData, postServiceData, activityServiceData, transactionsServiceData);
        }
    }

    public ProfileServiceData getUsersProfileData() {
        userDataLogger.log(Level.INFO, "Getting users profile data", userName);
        return profileServiceData;
    }

    public PostServiceData getUsersPostsData() {
        userDataLogger.log(Level.INFO, "Getting users posts data", userName);
        return postServiceData;
    }

    public ActivityServiceData getUsersActivitiesData() {
        userDataLogger.log(Level.INFO, "Getting users activities", userName);
        return activityServiceData;
    }

    public TransactionsServiceData getUsersTransactionsData() {
        userDataLogger.log(Level.INFO, "Getting users transactions", userName);
        return transactionsServiceData;
    }

    public String getUserName() {
        return userName;
    }
}
