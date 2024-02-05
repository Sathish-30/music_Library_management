package com.dexter.controller;

import com.dexter.model.User;
import com.dexter.service.UserService;

import java.util.Optional;

public class UserController  {
    public static boolean addUser(String uName , String emailId , String password){
        if(!UserService.checkUserAlreadyInDb(emailId)) {
            return UserService.addUserToDb(uName, emailId, password);
        }
        return false;
    }


    public static boolean loginUser(String emailId, String password) {
        return UserService.CheckUserCredentials(emailId , password);
    }

    public static Optional<User> getUserByEmailId(String emailId) {
        return UserService.getUserFromDb(emailId);
    }

    public static void viewAllTheSongs() {
        UserService.getAllSongsFromDB();
    }

    public static void viewAllByPublisher(String pName) {
        UserService.getAllPublisherSongsFromDB(pName);
    }
}
