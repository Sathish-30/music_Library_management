package com.dexter.queries;

public class PublisherPrivilegesQueries {
    public static String addSong(){
        return "INSERT INTO songs(songName , publisherId , genre , language , releasedate) VALUES (? , ? , ? , ? , CURRENT_DATE)";
    }
}
