package edu.najah.cap.delete.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;

import java.time.Instant;
import java.util.List;

public class PostsDeleter implements IServiceDeleter {
    private final IPostService postService;

    public PostsDeleter(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public synchronized void delete(String username) {
        List<Post> posts;
        try {
            posts = postService.getPosts(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var postIterator = posts.iterator();

        while (postIterator.hasNext()) {
            if (Instant.now().getEpochSecond() % 3 == 0) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {}
            }
            try {
                var post = postIterator.next();
                postIterator.remove();
                postService.deletePost(username, post.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
            }
        }
    }
}
