package com.dexter.controller;

import com.dexter.model.Publisher;
import com.dexter.service.PublisherService;

import java.util.Optional;

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

    public static Optional<Publisher> getPublisherByEmailId(String emailId) {
        return PublisherService.getPublisherByEmailIdFromDb(emailId);
    }

    public static boolean addSongToDb(String sName, int publisherId, String genre, String language) {
        return PublisherService.addSongToDB(sName , publisherId , genre , language);
    }

    public static boolean deleteSong(int publisherId, String sName) {
        return PublisherService.deleteSongByPublisher(publisherId , sName);
    }
}
