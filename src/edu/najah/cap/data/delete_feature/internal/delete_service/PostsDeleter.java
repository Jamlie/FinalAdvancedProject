package edu.najah.cap.data.delete_feature.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostsDeleter implements IServiceDeleter {
    private final IPostService postService;
    private static final Logger logger = Logger.getLogger(PostsDeleter.class.getName());

    public PostsDeleter(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public synchronized void delete(String username) {
        List<Post> posts;
        try {
            posts = postService.getPosts(username);
        } catch (SystemBusyException e) {
            logger.log(Level.WARNING, "Warning: Failed to get posts for user {0}", username);
            return;
        } catch (BadRequestException e) {
            logger.log(Level.SEVERE, "Error: Failed to get posts for user {0}", username);
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            logger.log(Level.INFO, "Warning: No posts for user {0}", username);
            return;
        }

        var postIterator = posts.iterator();

        while (postIterator.hasNext()) {
            if (Instant.now().getEpochSecond() % 3 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {}
            }
            try {
                var post = postIterator.next();
                postIterator.remove();
                postService.deletePost(username, post.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException ignored) {
                logger.log(Level.WARNING, "Warning: Failed to delete post for user {0}", username);
            }
        }
    }
}
