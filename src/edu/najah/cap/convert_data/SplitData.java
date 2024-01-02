package edu.najah.cap.convert_data;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.data.Data_Exporting.DataCollection.UserData;
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
        listOfData.add(UserProfileData(userData));

        if(userData.getPosts() != null){
            listOfData.add(ListOfPosts(userData));
        }
        if(userData.getUserActivities() != null){
            listOfData.add(ListOfActivities(userData));
        }
        if(userData.getTransactions() != null) {
            listOfData.add(ListOfPayments(userData));
        }

        return listOfData;
    }
    public List<String> UserProfileData(UserData userData){
        List<String> userProfileData = new ArrayList<>();
        var user = userData.getUserProfile();
        userProfileData.add("User Name = " + user.getUserName());
        userProfileData.add("User Building = " + user.getBuilding());
        userProfileData.add("First Name = " + user.getFirstName());
        userProfileData.add("Last Name = " + user.getLastName());
        userProfileData.add("Phone Number = " + user.getPhoneNumber());
        userProfileData.add("Email = " + user.getEmail());
        userProfileData.add("Role = " + user.getRole());
        userProfileData.add("Department = " + user.getDepartment());
        userProfileData.add("Organization = " + user.getOrganization());
        userProfileData.add("Country = " + user.getCountry());
        userProfileData.add("City = " + user.getCity());
        userProfileData.add("Street = " + user.getStreet());
        userProfileData.add("Postal Code = " + user.getPostalCode());
        userProfileData.add("User Type = " + user.getUserType());
        SplitDataLogger.log(Level.INFO, "User profile data extracted successfully");
        return userProfileData;
    }
    public List<String> ListOfPosts(UserData userData){
        List<String> listOfPosts = new ArrayList<>(); 
        var posts = userData.getPosts();

        for (Post post : posts) {
            listOfPosts.add("ID = " + post.getId());
            listOfPosts.add("Date = " + post.getDate());
            listOfPosts.add("Title = " + post.getTitle());
            listOfPosts.add("Body = " + post.getBody());
            listOfPosts.add("Author = " + post.getAuthor());
            listOfPosts.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "Post data extracted successfully");

        return listOfPosts;
    }

    public List<String> ListOfActivities(UserData userData){
        List<String> listOfActivities = new ArrayList<>();
        var userActivities = userData.getUserActivities(); 

        for (UserActivity activity : userActivities) {
            listOfActivities.add("Activity ID = " + activity.getId());
            listOfActivities.add("User ID = " + activity.getUserId());
            listOfActivities.add("Activity Type = " + activity.getActivityType());
            listOfActivities.add("Activity Date = " + activity.getActivityDate());
            listOfActivities.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "User activity data extracted successfully");
        return listOfActivities;
    }
    
    public List<String> ListOfPayments(UserData userData){
        List<String> listOfPayments = new ArrayList<>();
        var userPayment = userData.getTransactions();

        listOfPayments.add("Balance = " + userData.getBalance() + "\n\n\n");
        for (Transaction payment : userPayment) {
            listOfPayments.add("Payment ID = " + payment.getId());
            listOfPayments.add("Payment User Name = " + payment.getUserName());
            listOfPayments.add("Payment Description = " + payment.getDescription());
            listOfPayments.add("Payment Amount = " + payment.getAmount());
            listOfPayments.add("\n\n\n");
        }
        SplitDataLogger.log(Level.INFO, "Payment data extracted successfully");
        return listOfPayments;
    }
}