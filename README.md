Final project template 
======================
This is the template for the final project in the course. Your application should be run on [Application.java](src%2Fedu%2Fnajah%2Fcap%2Fdata%2FApplication.java)

**Do not change any existing code in the template. You can add new classes and methods as you see fit.**

## Project Description

### AllUsersData Package
This Package has four classes ```ActivityServiceData```, ```PostServiceData```, ```ProfileServiceData``` and ```TransactionsServiceData``` each one of them do the following:
- ```ActivityServiceData``` in its constructor takes a IUserActivityService object and in its getUserActivities method return a List<UserActivity>.
- ```PostServiceData``` in its constructor takes a IPostService object and in its getUserPosts method return a List<Post>.
- ```ProfileServiceData``` in its constructor takes a IUserService object and in its getUserProfile method return a UserProfile object.
- ```TransactionsServiceData``` in its constructor takes a IPayment object and in its getUserTransactions method return a List<Transaction> also getBalance method return a double object.
