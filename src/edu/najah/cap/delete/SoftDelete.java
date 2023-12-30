package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.database.*;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.sql.SQLException;
import java.util.List;

public class SoftDelete extends Delete {
    private IUserActivityService userActivityService;
    private IUserService userService;
    private IPostService postService;
    private IPayment paymentService;
    private Database<SoftDeletedUsersModel> deletedUsersDatabase;
    private String dbName = "deleted_users.db";

    private SoftDelete(IUserActivityService userActivityService, IUserService userService, IPostService postService, IPayment paymentService, DatabaseType type) {
        this.userActivityService = userActivityService;
        this.userService = userService;
        this.postService = postService;
        this.paymentService = paymentService;
        deletedUsersDatabase = SoftDeletedUsersDatabase.getInstance(type, dbName);
    }

    public static class Builder {
        private IUserActivityService userActivityService;
        private IUserService userService;
        private IPostService postService;
        private IPayment paymentService;
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

        public Delete build() {
            return new SoftDelete(userActivityService, userService, postService, paymentService, type);
        }
    }

    @Override
    public synchronized void delete(String username) {
        String email, password;
        try {
            UserProfile userProfile = userService.getUser(username);
            email = userProfile.getEmail();
            password = userProfile.getPassword();
        } catch (NotFoundException | SystemBusyException | BadRequestException e) {
            throw new RuntimeException(e);
        }

        List<Runnable> runnable = getRunnable(username);
        runnable.forEach(Runnable::run);

        try {
            deletedUsersDatabase.connect();
            deletedUsersDatabase.insert(new SoftDeletedUsersModel(username, email, password));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                deletedUsersDatabase.disconnect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Runnable> getRunnable(String username) {
        return getRunnableAndCheck(username, postService, userActivityService, userService, paymentService);
    }
}
