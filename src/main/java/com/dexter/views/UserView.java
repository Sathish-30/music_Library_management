package com.dexter.views;

import com.dexter.controller.UserController;
import com.dexter.model.User;

import java.util.Optional;

public class UserView {
    public static void userView(String emailId){
        Optional<User> userOptional = UserController.getUserByEmailId(emailId);
        if(userOptional.isPresent()){
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println(userOptional.get());
            System.out.println("---------------------------------------------------------------------------- \n");
        }else{
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println("Failed to fetch user");
            System.out.println("---------------------------------------------------------------------------- \n");
        }
    }
}
