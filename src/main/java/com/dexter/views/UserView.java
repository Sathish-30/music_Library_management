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
            User user = userOptional.get();
            while (true) {
                System.out.println("1. View all the songs");
                System.out.println("2. View album By publisher");
                System.out.println("3. View album by genre");
                System.out.println("4. Add song to Playlist by song id");
                System.out.println("5. Delete song from Playlist");
                System.out.println("6. View all the songs from the Playlist");
                System.out.println("7. Subscribe Account");
                System.out.println("8. Exit");
                System.out.print("Enter the operation to be performed : ");
                int operation = in.nextInt();

                if (operation == 1) {
                    UserController.viewAllTheSongs();
                }

                if (operation == 2) {
                    UserController.getListOfPublishers();
                    in.nextLine();
                    System.out.print("Enter the publisher name : ");
                    String pName = in.nextLine();
                    UserController.viewAllSongsByPublisher(pName);
                }

                if(operation == 3){
                    UserController.getListOfGenre();
                    in.nextLine();
                    System.out.print("Enter the genre to be searched : ");
                    String genre = in.next();
                    UserController.getSongByGenre(genre);
                }

                if(operation == 4){
                    UserController.viewAllTheSongs();
                    System.out.print("Enter the song id :  ");
                    int songId = in.nextInt();
                    UserController.addSongToPlayList(songId , user.getUserId() ,user.getPlayListId());
                }

                if(operation == 5){
                    UserController.getAllSongsFromPlayList(user.getPlayListId());
                    System.out.print("Enter the song id of the song which needed to be deleted : ");
                    int songId = in.nextInt();
                    UserController.deleteSongToPlayList(songId , user.getPlayListId());
                }

                if(operation == 6){
                    UserController.getListOfSongsFromThePlayList(user.getPlayListId());
                }

                if(operation == 7){
                    System.out.println("Subscription Amount - 199 ");
                    System.out.print("Enter the amount to add subscription : ");
                    int amount = in.nextInt();
                }

                if (operation == 8) break;
            }
        }else{
            System.out.println("---------------------------------------------------------------------------- \n");
            System.out.println("Failed to fetch user");
            System.out.println("---------------------------------------------------------------------------- \n");
        }
    }
}
