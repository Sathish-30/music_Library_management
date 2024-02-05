package com.dexter.views;


import com.dexter.controller.PublisherController;
import com.dexter.controller.UserController;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static void landingPage(){

        while(true) {
            System.out.println("1. Add User");
            System.out.println("2. Login User");
            System.out.println("3. Add Publisher");
            System.out.println("4. Login Publisher");
            System.out.println("5. Exit");
            System.out.print("Enter the operation to be performed : ");
            int operation = in.nextInt();
            // Operation 1 for adding user to the database
            if(operation == 1){

                in.nextLine();
                System.out.print("Enter the user name : ");
                String uName = in.nextLine();
                System.out.print("Enter the user emailId : ");
                String emailId = in.nextLine();
                System.out.print("Enter the user password : ");
                String password = in.nextLine();
                System.out.println();

                // addUser is called
                boolean status = UserController.addUser(uName , emailId , password);
                if(status) {
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User signed up successfully");
                    System.out.println("---------------------------------------------------------------------------- \n");
                    UserView.userView(emailId);
                }else{
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User Already exists");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }
            }

            //Operation 2 is for User login
            if(operation == 2){
                in.nextLine();
                System.out.print("Enter the emailId : ");
                String emailId = in.nextLine();
                System.out.print("Enter the password : ");
                String password = in.nextLine();
                System.out.println();
                boolean status = UserController.loginUser(emailId , password);
                if(status) {
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User Logged in successfully");
                    System.out.println("---------------------------------------------------------------------------- \n");
                    UserView.userView(emailId);
                }else{
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("Invalid credentials");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }
            }

            // Operation 3 for adding user to the database
            if(operation == 3){

                in.nextLine();
                System.out.print("Enter the publisher name : ");
                String pName = in.nextLine();
                System.out.print("Enter the publisher emailId : ");
                String emailId = in.nextLine();
                System.out.print("Enter the publisher password : ");
                String password = in.nextLine();
                System.out.println();

                // addPublisher is called
                boolean status = PublisherController.addPublisher(pName , emailId , password);
                if(status) {
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("publisher signed up successfully");
                    System.out.println("---------------------------------------------------------------------------- \n");
                    PublisherView.publisherView(emailId);
                }else{
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("Publisher Already exists");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }
            }

            // Operation 4 for publisher login
            if(operation == 4){
                in.nextLine();
                System.out.print("Enter the emailId : ");
                String emailId = in.nextLine();
                System.out.print("Enter the password : ");
                String password = in.nextLine();
                System.out.println();
                boolean status = PublisherController.loginPublisher(emailId , password);
                if(status) {
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("Publisher Logged in successfully");
                    System.out.println("---------------------------------------------------------------------------- \n");
                    PublisherView.publisherView(emailId);
                }else{
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("Invalid credentials");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }

            }
            // base case to terminate loop
            if (operation == 5) break;
        }
    }
}
