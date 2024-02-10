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

    public static void viewAllSongsByPublisher(String pName) {
        UserService.getAllPublisherSongsFromDB(pName);
    }

    public static void getSongByGenre(String genre) {
        UserService.getAllSongsFromDbByGenre(genre);
    }

    public static void getListOfGenre() {
        UserService.getListOfGenreFromDb();
    }

    public static void addSongToPlayList(int songId , int userId ,  int playListId) {
        if(UserService.checkSongIsPresent(songId)){
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println("Song not available");
            System.out.print("---------------------------------------------------------------------------- \n");
        }
        else if(UserService.checkUserHavePremiumAccount(userId , playListId)) {
            if(!UserService.checkSongInPlayList(playListId , songId)) {
                UserService.addSongsToPlayList(songId, playListId);
            }else{
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println("Song is Already present in the database");
                System.out.print("---------------------------------------------------------------------------- \n");
            }
        }else{
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println("Subscribe to the premium plan to add more songs to the playlist");
            System.out.print("---------------------------------------------------------------------------- \n");
        }
    }

    public static void deleteSongToPlayList(int songId, int playListId) {
        System.out.print("---------------------------------------------------------------------------- \n");
        if(UserService.deleteSongFromPlayList(songId , playListId)){
            System.out.println("Song deleted from the playlist......");
        }else{
            System.out.println("Unable to delete song from the playlist");
        }
        System.out.print("---------------------------------------------------------------------------- \n");

    }

    public static void getListOfSongsFromThePlayList(int playListId) {
        UserService.getSongsFromThePlayList(playListId);
    }

    public static void getListOfPublishers() {
        UserService.getListOfPublishers();
    }

    public static void getAllSongsFromPlayList(int playListId) {
        UserService.getAllSongFromPlayList(playListId);
    }
}
