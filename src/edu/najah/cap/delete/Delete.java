package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.delete_service.*;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.util.ArrayList;
import java.util.List;

public abstract class Delete {
    abstract public void delete(String username);

    final protected List<Runnable> getRunnables(String username,
                                                 IPostService postService,
                                                 IUserActivityService userActivityService,
                                                 IUserService userService,
                                                 UserType userType,
                                                 IPayment paymentService) {
        List<Runnable> runnables = new ArrayList<>(List.of(
                () -> new DeletingService<>(new PostsDeleter(postService)).delete(username),
                () -> new DeletingService<>(new UserActivityDeleter(userActivityService)).delete(username),
                () -> new DeletingService<>(new UserServiceDeleter(userService)).delete(username)
        ));

        if (userType == UserType.PREMIUM_USER) {
            runnables.add(() -> new DeletingService<>(new PaymentDeleter(paymentService)).delete(username));
        }

        return runnables;
    }
}
