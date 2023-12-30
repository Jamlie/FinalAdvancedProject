package edu.najah.cap.dataCollection.UserTypes;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.UserData;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.posts.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CollectDataForRegularUser implements CollectData {
    private static final Logger collectDataForRegularUserLogger = Logger.getLogger(CollectDataForRegularUser.class.getName());
    private final UserData userData;
    private final UserProfile userProfile;
    private final List<Post> posts;
    private final List<UserActivity> userActivities;

    public UserProfile getUserProfile() {
        return userProfile;
    }
    public List<Post> getPosts(){
        return posts;
    }
    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public CollectDataForRegularUser(UserData userData){
        this.userData = userData;
        this.userProfile = userData.getUserProfile();
        this.posts = userData.getPosts();
        this.userActivities = userData.getUserActivities();
    }
    @Override
    public UserData collect() {
        try {
            collectDataForRegularUserLogger.log(Level.INFO, "Collecting data for regular user: {0}", userProfile.getUserName());
            return userData;
        } catch (Exception e) {
            collectDataForRegularUserLogger.log(Level.SEVERE, "Unexpected error occurred while collecting data for regular user: {0}", new Object[]{userProfile.getUserName(), e});
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}
