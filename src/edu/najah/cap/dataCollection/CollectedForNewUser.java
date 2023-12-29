package edu.najah.cap.dataCollection;

import edu.najah.cap.iam.UserProfile;

public class CollectedForNewUser implements CollectedData {

    private final UserData userData;
    private final UserProfile userProfile;
    public CollectedForNewUser(UserData userData){
        this.userProfile = userData.getUserProfile();
        this.userData = userData;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public UserData collect() { return userData; }
}
