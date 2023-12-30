package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPostsData {
    private static final Logger userPostsDataLogger = Logger.getLogger(UserPostsData.class.getName());
    private static IPostService postService;

    public UserPostsData( IPostService postService) {
        UserPostsData.postService = postService;
    }

    public List<Post> getPosts(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        try {
            userPostsDataLogger.log(Level.INFO, "Fetching posts for userName: {0}", userName);
            return postService.getPosts(userName);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            userPostsDataLogger.log(Level.WARNING, "Error fetching posts for userName: {0}", new Object[]{userName, e});
            throw e;
        } catch (Exception e) {
            userPostsDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching posts for userName: {0}", new Object[]{userName, e});
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

}
