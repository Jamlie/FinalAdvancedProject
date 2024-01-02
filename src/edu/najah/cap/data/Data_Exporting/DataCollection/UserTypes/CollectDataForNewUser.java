package edu.najah.cap.data.Data_Exporting.DataCollection.UserTypes;

import edu.najah.cap.data.Data_Exporting.DataCollection.UserData;
import edu.najah.cap.iam.UserProfile;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CollectDataForNewUser implements CollectData {
    private static final Logger collectDataForNewUserLogger = Logger.getLogger(CollectDataForNewUser.class.getName());
    private final UserData userData;
    private final UserProfile userProfile;
    public CollectDataForNewUser(UserData userData){
        this.userProfile = userData.getUserProfile();
        this.userData = userData;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public UserData collect() {
        try {
        collectDataForNewUserLogger.log(Level.INFO, "Collecting data for new user: {0}", userProfile.getUserName());
        return userData;
        } catch (Exception e) {
            collectDataForNewUserLogger.log(Level.SEVERE, "Unexpected error occurred while collecting data for user: {0}", new Object[]{userProfile.getUserName(), e});
            throw e;
        }
    }
}
