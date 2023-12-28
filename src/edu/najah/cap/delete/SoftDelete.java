package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.database.*;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.util.List;

public class SoftDelete extends Delete {
    private IUserActivityService userActivityService;
    private IUserService userService;
    private IPostService postService;
    private IPayment paymentService;
    private Database<SoftDeletedUsersModel> deletedUsersDatabase;
    private String dbName = "deleted_users.db";
    private UserType userType;

    private SoftDelete(IUserActivityService userActivityService, IUserService userService, IPostService postService, IPayment paymentService, UserType userType, DatabaseType type) {
        this.userActivityService = userActivityService;
        this.userService = userService;
        this.postService = postService;
        this.paymentService = paymentService;
        this.userType = userType;
        deletedUsersDatabase = SoftDeletedUsersDatabase.getInstance(type, dbName);
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

        public Delete build() {
            return new SoftDelete(userActivityService, userService, postService, paymentService, userType,type);
        }
    }

    @Override
    public synchronized void delete(String username) {
        UserProfile user;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            System.err.println("Error while getting user");
            return;
        }
        String email = user.getEmail();
        String password = user.getPassword();

        List<Runnable> runnables = getRunnables(username);

        runnables.parallelStream().forEach(Runnable::run);

        System.out.println(username);
        System.out.println(email);
        System.out.println(password);

        try {
            deletedUsersDatabase.connect();
            deletedUsersDatabase.insert(new SoftDeletedUsersModel(username, email, password));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                deletedUsersDatabase.disconnect();
            } catch (Exception e) {
                System.err.println("Error while disconnecting from deleted users database");
            }
        }
    }

    private List<Runnable> getRunnables(String username) {
        return getRunnables(username, postService, userActivityService, userService, this.userType, paymentService);
    }
}
