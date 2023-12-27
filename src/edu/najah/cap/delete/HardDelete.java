package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.database.Database;
import edu.najah.cap.delete.internal.database.DeletedUsersDatabase;
import edu.najah.cap.delete.internal.database.DeletedUsersModel;
import edu.najah.cap.delete.internal.delete_service.*;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class HardDelete {
    private IUserActivityService userActivityService;
    private IUserService userService;
    private IPostService postService;
    private IPayment paymentService;
    private Database<DeletedUsersModel> deletedUsersDatabase;
    private String dbName = "deleted_users.db";
    private UserType userType;

    private HardDelete(IUserActivityService userActivityService, IUserService userService, IPostService postService, IPayment paymentService, UserType userType,DatabaseType type) {
        this.userActivityService = userActivityService;
        this.userService = userService;
        this.postService = postService;
        this.paymentService = paymentService;
        this.userType = userType;
        deletedUsersDatabase = DeletedUsersDatabase.getInstance(type, dbName);
    }

    public static class Builder {
        private IUserActivityService userActivityService;
        private IUserService userService;
        private IPostService postService;
        private IPayment paymentService;
        private UserType userType;
        private DatabaseType type;

        public Builder setUserActivityService(IUserActivityService userActivityService) {
            this.userActivityService = userActivityService;
            return this;
        }

        public Builder setUserService(IUserService userService) {
            this.userService = userService;
            return this;
        }

        public Builder setPostService(IPostService postService) {
            this.postService = postService;
            return this;
        }

        public Builder setPaymentService(IPayment paymentService) {
            this.paymentService = paymentService;
            return this;
        }

        public Builder setDatabaseType(DatabaseType type) {
            this.type = type;
            return this;
        }

        public Builder setUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public HardDelete build() {
            return new HardDelete(userActivityService, userService, postService, paymentService, userType,type);
        }
    }


    public synchronized void delete(String username) {
        List<Runnable> runnables = List.of(
                () -> new DeletingService<>(new PaymentDeleter(paymentService)).delete(username),
                () -> new DeletingService<>(new PostsDeleter(postService)).delete(username),
                () -> new DeletingService<>(new UserActivityDeleter(userActivityService)).delete(username),
                () -> new DeletingService<>(new UserServiceDeleter(userService)).delete(username)
        );


        runnables.parallelStream().forEach(Runnable::run);

        try {
            deletedUsersDatabase.connect();
            deletedUsersDatabase.insert(new DeletedUsersModel(username));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                deletedUsersDatabase.disconnect();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}