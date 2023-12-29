package edu.najah.cap.dataCollection;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.util.List;

public class CollectedForRegularUser implements CollectedData {

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

    public CollectedForRegularUser(UserData userData){
        this.userData = userData;
        this.userProfile = userData.getUserProfile();
        this.posts = userData.getPosts();
        this.userActivities = userData.getUserActivities();
    }
    @Override
    public UserData collect() { return userData; }
}
