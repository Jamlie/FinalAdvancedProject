package edu.najah.cap.data.delete_feature;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.data.delete_feature.internal.database.Database;
import edu.najah.cap.data.delete_feature.internal.database.HardDeletedUsersDatabase;
import edu.najah.cap.data.delete_feature.internal.database.HardDeletedUsersModel;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HardDelete extends Delete {
    private IUserActivityService userActivityService;
    private IUserService userService;
    private IPostService postService;
    private IPayment paymentService;
    private Database<HardDeletedUsersModel> deletedUsersDatabase;
    private String dbName = "deleted_users.db";
    private static final Logger logger = Logger.getLogger(HardDelete.class.getName());

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
            logger.log(Level.INFO, "User {0} deleted successfully", username);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Error while inserting into database", sqle);
            throw new RuntimeException(sqle);
        } finally {
            try {
                deletedUsersDatabase.disconnect();
            } catch (SQLException sqle) {
                logger.log(Level.SEVERE, "Error while disconnecting from database", sqle);
                throw new RuntimeException(sqle);
            }
        }
    }

    private List<Runnable> getRunnable(String username) {
        return getRunnableAndCheck(username, postService, userActivityService, userService, paymentService);
    }
}