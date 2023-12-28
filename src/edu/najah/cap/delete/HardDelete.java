package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.database.Database;
import edu.najah.cap.delete.internal.database.HardDeletedUsersDatabase;
import edu.najah.cap.delete.internal.database.HardDeletedUsersModel;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.sql.SQLException;
import java.util.List;

public class HardDelete extends Delete {
    private IUserActivityService userActivityService;
    private IUserService userService;
    private IPostService postService;
    private IPayment paymentService;
    private Database<HardDeletedUsersModel> deletedUsersDatabase;
    private String dbName = "hard_deleted_users.db";
    private UserType userType;

    private HardDelete(IUserActivityService userActivityService, IUserService userService, IPostService postService, IPayment paymentService, UserType userType, DatabaseType type) {
        this.userActivityService = userActivityService;
        this.userService = userService;
        this.postService = postService;
        this.paymentService = paymentService;
        this.userType = userType;
        deletedUsersDatabase = HardDeletedUsersDatabase.getInstance(type, dbName);
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
            return new HardDelete(userActivityService, userService, postService, paymentService, userType,type);
        }
    }


    @Override
    public synchronized void delete(String username) {
        List<Runnable> runnables = getRunnables(username);

        runnables.parallelStream().forEach(Runnable::run);

        try {
            deletedUsersDatabase.connect();
            deletedUsersDatabase.insert(new HardDeletedUsersModel(username));
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

    private List<Runnable> getRunnables(String username) {
        return getRunnables(username, postService, userActivityService, userService, this.userType, paymentService);
    }
}