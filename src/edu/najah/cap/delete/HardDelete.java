package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.delete.internal.database.Database;
import edu.najah.cap.delete.internal.database.HardDeletedUsersDatabase;
import edu.najah.cap.delete.internal.database.HardDeletedUsersModel;
import edu.najah.cap.iam.IUserService;
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
    private String dbName = "deleted_users.db";

    private HardDelete(IUserActivityService userActivityService, IUserService userService, IPostService postService, IPayment paymentService, DatabaseType type) {
        this.userActivityService = userActivityService;
        this.userService = userService;
        this.postService = postService;
        this.paymentService = paymentService;
        deletedUsersDatabase = HardDeletedUsersDatabase.getInstance(type, dbName);
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
            return new HardDelete(userActivityService, userService, postService, paymentService, type);
        }
    }


    @Override
    public synchronized void delete(String username) {
        List<Runnable> runnable = getRunnable(username);

        runnable.parallelStream().forEach(Runnable::run);

        try {
            deletedUsersDatabase.connect();
            deletedUsersDatabase.insert(new HardDeletedUsersModel(username));
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            try {
                deletedUsersDatabase.disconnect();
            } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        }
    }

    private List<Runnable> getRunnable(String username) {
        return getRunnableAndCheck(username, postService, userActivityService, userService, paymentService);
    }
}