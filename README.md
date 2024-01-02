Final project template 
======================
This is the template for the final project in the course. Your application should be run on [Application.java](src%2Fedu%2Fnajah%2Fcap%2Fdata%2FApplication.java)

**Do not change any existing code in the template. You can add new classes and methods as you see fit.**

## Project Description
### delete_feature
This package contains 4 classes, 1 enum and `internal` package:
* **DeleteType**: an enum that contains the type of the database, such as `SQLITE`, `MYSQL`, etc...
* **Delete**: an abstract class that contains the abstract method `delete()` and 2 methods that are essential for deleting `getRunnable()` and `getRunnableAndCheck()`. This helps in removing duplicate code and abstracts the checking of the `userType` whether it's `NEW_USER`, `REGULAR_USER` or `PREMIUM_USER`.
* **HardDelete**: a class that extends `Delete` and implements the `delete()` method. This class is used to delete the data while saving the username in the database.
* **SoftDelete**: a class that extends `Delete` and implements the `delete()` method. This class is used to delete the data while saving the username, email and password in the database.
* **SQLValidation**: a class that contains the validation methods for the SQL database.

### delete_feature.internal
This package contains 2 packages:
* **database**: a package that contains the essential files for the database.
* **delete_service**: a package that contains the classes that are used to delete the data of the other services.

### database
This package contains the following classes, records, and interfaces:
* **Database<T extends Model>**: an abstract class that contains abstract methods and two methods that help in avoiding duplicate code.
    * `connect()`
    * `disconnect()`
    * `createTable()`
    * `insert(T)`
    * `isConnected()`
    * `setConnected(boolean)`
    * `getConnection()`
    * `setConnection(Connection)`
    * `connect(DatabaseType, String)`
    * `connect(DatabaseType, String, Logger)`
* **DatabaseConnection**: a class that has the method `getConnection(DatabaseType, String)` that returns the connection of the database. It uses DatabaseConnectionFactory to get the appropriate connection of the database based on the type.
* **DatabaseConnectionFactory**: a class that has the method `getConnection(DatabaseType, String)` that returns the connection of the database based on the type. This helps in the Open-Closed Principle.
* **Model**: an interface to mark a record as a model so that it can be used in the database.
* **HardDeletedUserModel**: a record that implements the `Model` interface. It is used to store the username of the deleted user.
* **SoftDeletedUserModel**: a record that implements the `Model` interface. It is used to store the username, email and password of the deleted user.
* **HardDeletedUserDatabase**: a class that extends `Database<HardDeletedUserModel>` and implements the abstract methods. It is used to connect to the database of the hard deleted users and to insert the data. It also has the method `getInstance(DatabaseType, String)` that returns the instance of the database based on the type. This helps in the Singleton Pattern.
* **SoftDeletedUserDatabase**: a class that extends `Database<SoftDeletedUserModel>` and implements the abstract methods. It is used to connect to the database of the soft deleted users and to insert the data. It also has the method `getInstance(DatabaseType, String)` that returns the instance of the database based on the type. This helps in the Singleton Pattern.
* **SQLiteConnection**: a class that returns the connection of the SQLite database.

### delete_service
This package is used to delete the user's data based on the service that the user is using. It contains the following classes:
* **IServiceDeleter**: an interface that contains the method `delete(String)`.
* **PaymentDeleter**: a class that implements `IServiceDeleter` and has the method `delete(String)` that deletes the user's payment data (only works on premium users).
* **PostsDeleter**: a class that implements `IServiceDeleter` and has the method `delete(String)` that deletes the user's posts data.
* **UserActivityDeleter**: a class that implements `IServiceDeleter` and has the method `delete(String)` that deletes the user's activity data (does not work on new users).
* **UserServiceDeleter**: a class that implements `IServiceDeleter` and has the method `delete(String)` that deletes the user's data.

## helpers
This package contains the following helper classes:
* **PaymentServiceWrapper**: a class that wraps `PaymentService` and overrides the method `removeTransaction(String, String)` to delete the user's payment data in a faster way.
* **PostServiceWrapper**: a class that wraps `PostsService` and overrides the method `deletePost(String)` to delete the user's posts data in a faster way.
* **UserActivityServiceWrapper**: a class that wraps `UserActivityService` and overrides the method `deleteUserActivity(String)` to delete the user's activity data in a faster way.
* **UserTypeFetcher**: a class that contains the method `getUserType(String)` that returns the type of the user based on the username.