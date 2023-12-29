package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import edu.najah.cap.posts.PostService;

import java.util.List;

public class UserPostsData {
    private final IPostService postService;

    public UserPostsData( IPostService postService) {
        this.postService = postService;
    }

    public List<Post> getPosts(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        return postService.getPosts(userName);
    }

}
