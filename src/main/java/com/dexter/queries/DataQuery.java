package com.dexter.queries;

public class DataQuery {
    public static String getUserByEmailId(){
        return "SELECT * FROM users WHERE emailId = ?";
    }
    public static String getPublisherByEmailId(){
        return "SELECT * FROM publishers WHERE emailId = ?";
    }
}
