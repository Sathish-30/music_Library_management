package com.dexter.controller;

import com.dexter.service.UserService;

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
}
