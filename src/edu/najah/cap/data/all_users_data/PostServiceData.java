package edu.najah.cap.data.all_users_data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostServiceData {
    private static final Logger userPostsDataLogger = Logger.getLogger(PostServiceData.class.getName());
    private static IPostService postService;

    public PostServiceData(IPostService postService) {
        PostServiceData.postService = postService;
    }

    public List<Post> getUserPosts(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        try {
            userPostsDataLogger.log(Level.INFO, "Fetching posts for userName: {0}", userName);
            return postService.getPosts(userName);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            userPostsDataLogger.log(Level.SEVERE, "Error fetching posts for userName: {0}", new Object[]{userName, e});
            return null;
        } catch (Exception e) {
            userPostsDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching posts for userName: {0}", new Object[]{userName, e});
            return null;
        }
    }

}
