package edu.najah.cap.delete_feature;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete_feature.internal.delete_service.*;
import edu.najah.cap.helpers.UserTypeFetcher;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.helpers.PaymentServiceWrapper;
import edu.najah.cap.helpers.PostServiceWrapper;
import edu.najah.cap.helpers.UserActivityServiceWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class Delete {
    abstract public void delete(String username);
    private static Logger logger = Logger.getLogger(Delete.class.getName());

    final protected List<Runnable> getRunnables(String username,
                                                 IPostService postService,
                                                 IUserActivityService userActivityService,
                                                 IUserService userService,
                                                 IPayment paymentService) {

        List<Runnable> runnables = new ArrayList<>(List.of(
                () -> new ServiceDeleter<>(new PostsDeleter(postService)).delete(username),
                () -> new ServiceDeleter<>(new UserServiceDeleter(userService)).delete(username)
        ));

        if (Objects.nonNull(userActivityService)) {
            runnables.add(() -> new ServiceDeleter<>(new UserActivityDeleter(userActivityService)).delete(username));
        }

        if (Objects.nonNull(paymentService)) {
            runnables.add(() -> new ServiceDeleter<>(new PaymentDeleter(paymentService)).delete(username));
        }

        logger.info("Created runnable for user: " + username);
        return runnables;
    }

    protected final List<Runnable> getRunnableAndCheck(String username, IPostService postService, IUserActivityService userActivityService, IUserService userService, IPayment paymentService) {
        PostServiceWrapper postServiceWrapper = new PostServiceWrapper(postService);
        UserActivityServiceWrapper userActivityServiceWrapper = new UserActivityServiceWrapper(userActivityService);
        PaymentServiceWrapper paymentServiceWrapper = new PaymentServiceWrapper(paymentService);

        new UserTypeFetcher(userService);
        UserType userType = UserTypeFetcher.getUserType(username);

        if (userType != UserType.PREMIUM_USER) {
            paymentServiceWrapper = null;
        }

        if (userType == UserType.NEW_USER) {
            userActivityServiceWrapper = null;
        }

        return getRunnables(username, postServiceWrapper, userActivityServiceWrapper, userService, paymentServiceWrapper);
    }
}
