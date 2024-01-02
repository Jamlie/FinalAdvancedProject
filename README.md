Final project template 
======================
This is the template for the final project in the course. Your application should be run on [Application.java](src%2Fedu%2Fnajah%2Fcap%2Fdata%2FApplication.java)

**Do not change any existing code in the template. You can add new classes and methods as you see fit.**

## Project Description

### all-usersData Package
This Package has four classes ```ActivityServiceData```, ```PostServiceData```, ```ProfileServiceData``` and ```TransactionsServiceData``` each one of them do the following:
- ```ActivityServiceData``` in its constructor takes a IUserActivityService object and in its getUserActivities method return a List<UserActivity>.
- ```PostServiceData``` in its constructor takes a IPostService object and in its getUserPosts method return a List<Post>.
- ```ProfileServiceData``` in its constructor takes a IUserService object and in its getUserProfile method return a UserProfile object.
- ```TransactionsServiceData``` in its constructor takes a IPayment object and in its getUserTransactions method return a List<Transaction> also getBalance method return a double object.

### data_exporting Package 
#### DataCollection 
This package continues ```UsersData``` , ```UserData``` and ```user_types```package:
- ```UsersData``` class that takes ProfileServiceData, PostServiceData, ActivityServiceData, TransactionsServiceData and String and implements it using buillder design pattern.
- ```UserData``` class that takes UserProfile, List<Post>, List<UserActivity>, List<Transaction> and double and implements it using buillder design pattern.
- ##### user_types Package
  This package contains:
  - ```CollectData``` a interface contains a collect method that returns UserData object  
  - ```CollectDataForNewUser``` a class implements CollectData and contain needed information for new user, UserData object       and overrided collect method.
  - ```CollectDataForRegularUser``` a class implements CollectData and contain needed information for Regular user, UserData     object and overrided collect method.
  - ```CollectDataForPremiumUser``` a class implements CollectData and contain needed information for Premium user, UserData     object and overrided collect method.
  -  ```CollectDataForFactory```  a class implemented to verifi __factory design pattern__ this class get a UserData object      and it has a ```getCollectionDataFor``` method that returns a CollectData object based on __user type__ with it could be       (```CollectDataForNewUser```, ```CollectDataForRegularUser```, ```CollectDataForPremiumUser```).
  
### export Package
#### export_types 
This package contains:
- ```Export``` a interface contains a export method. 
- ```DirectExport``` a class implements Export and contain CollectDataForFactory object that contains user data to export using overrided export method.
- ```ExportToFileStorageService``` a class implements Export and contain CollectDataForFactory object that contains user data to export using overrided export method to file storage service picked by the user.
- ```ExportType``` contain export types enums.
- ```ExportTypeFactory``` a class contains ExportType and CollectDataForFactory objects and using ```getExportType``` method that returns Export object based on value of ExportType object with CollectDataForFactory object.

#### file_storage_service
This package contains:
- ```FileStorageService``` a interface contains a exportToFileStorageService method.
- ```ExportToDrive``` a class the override exportToFileStorageService method to export zip file to drive file storage services.
- ```ExportToDropbox``` a class the override exportToFileStorageService method to export zip file to dropbox file storage services.
- ```FilesStorageServiceFactory``` a class decid which service to export file to based on fileStorageServiceName value. 

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
