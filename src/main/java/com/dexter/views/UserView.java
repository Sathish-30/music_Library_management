package com.dexter.views;

import com.dexter.controller.UserController;
import com.dexter.model.User;

import java.util.Optional;
import java.util.Scanner;

public class UserView {
    private static final Scanner in = Main.in;
    public static void userView(String emailId){
        Optional<User> userOptional = UserController.getUserByEmailId(emailId);
        if(userOptional.isPresent()){
            System.out.print("---------------------------------------------------------------------------- \n");
            User user = userOptional.get();
            System.out.println(user);
            System.out.println("---------------------------------------------------------------------------- \n");
            while (true) {
                System.out.println("1. View all songs");
                System.out.println("2. View album By publisher");
                System.out.println("3. View album by genre");
                System.out.println("4. Add song to Playlist");
                System.out.println("5. Delete song from Playlist");
                System.out.println("6. Exit");
                System.out.print("Enter the operation to be performed : ");
                int operation = in.nextInt();

                if (operation == 1) {
                    UserController.viewAllTheSongs();
                }

                if (operation == 2) {
                    in.nextLine();
                    System.out.print("Enter the publisher name : ");
                    String pName = in.nextLine();
                    UserController.viewAllByPublisher(pName);
                }

                if (operation == 6) break;
            }
        }else{
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println("Failed to fetch user");
            System.out.println("---------------------------------------------------------------------------- \n");
        }
    }
}
