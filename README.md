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

### Data_Exporting Package 
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
  
### Export
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
