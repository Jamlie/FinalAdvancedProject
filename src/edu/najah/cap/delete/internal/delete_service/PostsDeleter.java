package edu.najah.cap.delete.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;

import java.time.Instant;
import java.util.List;

public class PostsDeleter implements IServiceDeleter {
    private IPostService postService;

    public PostsDeleter(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public synchronized void delete(String username) {
        List<Post> posts;
        try {
            posts = postService.getPosts(username);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        var postIterator = posts.iterator();

        while (postIterator.hasNext()) {
            try {
//                try {
//                    if (Instant.now().getEpochSecond() % 3 == 0) {
//                        Thread.sleep(1);
//                    }
//                } catch (Exception e) {}
                var post = postIterator.next();
                postIterator.remove();
                postService.deletePost(username, post.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
            }
        }
    }
}
