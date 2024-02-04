package com.dexter.controller;

import com.dexter.service.PublisherService;
import com.dexter.service.UserService;

public class PublisherController {
    public static boolean addPublisher(String pName , String emailId , String password){
        if(!PublisherService.checkUserAlreadyInDb(emailId)) {
           return PublisherService.addPublisherToDb(pName, emailId, password);
        }
        return false;
    }

    public static boolean loginPublisher(String emailId, String password) {
        return PublisherService.checkPublisherCredentials(emailId , password);
    }
}
