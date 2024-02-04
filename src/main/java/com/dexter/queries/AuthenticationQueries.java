package com.dexter.queries;

public class AuthenticationQueries {
    public static String userSignUp(){
        return "INSERT INTO users(userName , emailId , password) VALUES (? , ? , ?)";
    }

    public static String publisherSignUp(){
        return "INSERT INTO publishers(publisherName , emailId , password) VALUES (? , ? , ?)";
    }

    public static String userSignIn(){
        return "SELECT password FROM users WHERE emailId = ?";
    }

    public static String publisherSignIn(){
        return "SELECT password FROM publishers WHERE emailId = ?";
    }

    public static String userInDb(){
        return "SELECT password FROM users WHERE emailId = ?";
    }

    public static String publisherInDb(){
        return "SELECT password FROM publishers WHERE emailId = ?";
    }
}
