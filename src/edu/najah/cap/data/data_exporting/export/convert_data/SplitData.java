package edu.najah.cap.data.data_exporting.export.convert_data;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.data.data_exporting.data_collection.UserData;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplitData {

    private static final Logger SplitDataLogger = Logger.getLogger(SplitData.class.getName());
    public List<List<String>> split(UserData userData) {
        List<List<String>> listOfData = new ArrayList<>();
        listOfData.add(userProfileData(userData));

        if(userData.getPosts() != null){
            listOfData.add(listOfPosts(userData));
        }
        if(userData.getUserActivities() != null){
            listOfData.add(listOfActivities(userData));
        }
        if(userData.getTransactions() != null) {
            listOfData.add(listOfPayments(userData));
        }

        return listOfData;
    }
    public List<String> userProfileData(UserData userData){
        List<String> userProfileList = new ArrayList<>();
        var user = userData.getUserProfile();
        userProfileList.add("User Name = " + user.getUserName());
        userProfileList.add("User Building = " + user.getBuilding());
        userProfileList.add("First Name = " + user.getFirstName());
        userProfileList.add("Last Name = " + user.getLastName());
        userProfileList.add("Phone Number = " + user.getPhoneNumber());
        userProfileList.add("Email = " + user.getEmail());
        userProfileList.add("Role = " + user.getRole());
        userProfileList.add("Department = " + user.getDepartment());
        userProfileList.add("Organization = " + user.getOrganization());
        userProfileList.add("Country = " + user.getCountry());
        userProfileList.add("City = " + user.getCity());
        userProfileList.add("Street = " + user.getStreet());
        userProfileList.add("Postal Code = " + user.getPostalCode());
        userProfileList.add("User Type = " + user.getUserType());
        SplitDataLogger.log(Level.INFO, "User profile data extracted successfully");
        return userProfileList;
    }
    public List<String> listOfPosts(UserData userData){
        List<String> postsList = new ArrayList<>();
        var posts = userData.getPosts();

        for (Post post : posts) {
            postsList.add("ID = " + post.getId());
            postsList.add("Date = " + post.getDate());
            postsList.add("Title = " + post.getTitle());
            postsList.add("Body = " + post.getBody());
            postsList.add("Author = " + post.getAuthor());
            postsList.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "Post data extracted successfully");

        return postsList;
    }

    public List<String> listOfActivities(UserData userData){
        List<String> activitiesList = new ArrayList<>();
        var userActivities = userData.getUserActivities(); 

        for (UserActivity activity : userActivities) {
            activitiesList.add("Activity ID = " + activity.getId());
            activitiesList.add("User ID = " + activity.getUserId());
            activitiesList.add("Activity Type = " + activity.getActivityType());
            activitiesList.add("Activity Date = " + activity.getActivityDate());
            activitiesList.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "User activity data extracted successfully");
        return activitiesList;
    }
    
    public List<String> listOfPayments(UserData userData){
        List<String> paymentsList = new ArrayList<>();
        var userPayment = userData.getTransactions();

        paymentsList.add("Balance = " + userData.getBalance() + "\n\n\n");
        for (Transaction payment : userPayment) {
            paymentsList.add("Payment ID = " + payment.getId());
            paymentsList.add("Payment User Name = " + payment.getUserName());
            paymentsList.add("Payment Description = " + payment.getDescription());
            paymentsList.add("Payment Amount = " + payment.getAmount());
            paymentsList.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "Payment data extracted successfully");
        return paymentsList;
    }
}
