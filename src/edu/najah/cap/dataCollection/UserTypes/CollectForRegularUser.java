package edu.najah.cap.dataCollection.UserTypes;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.UserData;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.posts.Post;

import java.util.List;

public class CollectForRegularUser implements CollectData {

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

    public CollectForRegularUser(UserData userData){
        this.userData = userData;
        this.userProfile = userData.getUserProfile();
        this.posts = userData.getPosts();
        this.userActivities = userData.getUserActivities();
    }
    @Override
    public UserData collect() { return userData; }
}
