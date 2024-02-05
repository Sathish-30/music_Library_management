package com.dexter.views;

import com.dexter.controller.PublisherController;
import com.dexter.model.Publisher;

import java.util.Optional;
import java.util.Scanner;

public class PublisherView {
    private static final Scanner in = Main.in;

    public static void publisherView(String emailId){
        Optional<Publisher> publisherOptional = PublisherController.getPublisherByEmailId(emailId);
        if(publisherOptional.isPresent()){
            System.out.print("---------------------------------------------------------------------------- \n");
            Publisher publisher = publisherOptional.get();
            System.out.println(publisher);
            System.out.println("---------------------------------------------------------------------------- \n");
            while(true) {
                System.out.println("1. Add song");
                System.out.println("2. Delete song");
                System.out.println("3. Exit");
                System.out.print("Enter the operation to be performed : ");
                int n = in.nextInt();

                // Operation 1 for Add song
                // songName , publisherId , genre , language , releasedate
                if(n == 1){
                    in.nextLine();
                    System.out.print("Enter the song name : ");
                    String sName = in.nextLine();
                    System.out.print("Enter the song genre : ");
                    String genre = in.next();
                    in.nextLine();
                    System.out.print("Enter the song language : ");
                    String language = in.nextLine();
                    System.out.println();

                    boolean status = PublisherController.addSongToDb(sName , publisher.getPublisherId() , genre , language);
                    System.out.print("---------------------------------------------------------------------------- \n");
                    if(status) {
                        System.out.println("Song Added to the Application");
                    }else{
                        System.out.println("User Already exists");
                    }
                    System.out.println("---------------------------------------------------------------------------- \n");
                }

                // Operation 2 for Delete song
                if(n == 2){
                    in.nextLine();
                    System.out.print("Enter the song to be deleted from Application : ");
                    String sName = in.nextLine();
                    boolean status = PublisherController.deleteSong(publisher.getPublisherId() , sName);
                    System.out.print("---------------------------------------------------------------------------- \n");
                    if(status) {
                        System.out.println("Song Deleted from the Application");
                    }else{
                        System.out.println("Unable to delete the song");
                    }
                    System.out.println("---------------------------------------------------------------------------- \n");
                }

                if (n == 3) break;
            }
        }else{
            System.out.print("---------------------------------------------------------------------------- \n");
            System.out.println("Failed to fetch user");
            System.out.println("---------------------------------------------------------------------------- \n");
        }
    }
}
