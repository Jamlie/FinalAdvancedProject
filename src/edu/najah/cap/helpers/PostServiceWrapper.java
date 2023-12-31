package edu.najah.cap.helpers;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.Util;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.PostService;

public class PostServiceWrapper extends PostService {
    private IPostService postService;

    public PostServiceWrapper() {
        super();
    }

    public PostServiceWrapper(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public synchronized void deletePost(String author, String id) throws SystemBusyException, BadRequestException, NotFoundException {
        Util.validateUserName(author);
        var authorPosts = postService.getPosts(author);
        if (authorPosts != null) {
            authorPosts.removeIf(post -> post.getId().equals(id));
        }
    }
}
