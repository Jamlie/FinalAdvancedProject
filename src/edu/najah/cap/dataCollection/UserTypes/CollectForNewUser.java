package edu.najah.cap.dataCollection.UserTypes;

import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.UserData;
import edu.najah.cap.iam.UserProfile;

public class CollectForNewUser implements CollectData {

    private final UserData userData;
    private final UserProfile userProfile;
    public CollectForNewUser(UserData userData){
        this.userProfile = userData.getUserProfile();
        this.userData = userData;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public UserData collect() { return userData; }
}
