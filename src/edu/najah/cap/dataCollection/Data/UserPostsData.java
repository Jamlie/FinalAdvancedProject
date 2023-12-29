package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import java.util.List;

public class UserPostsData {
    private final IPostService postService;

    public UserPostsData( IPostService postService) {
        this.postService = postService;
    }

    public List<Post> getPosts(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        try {
            return postService.getPosts(userName);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

}
