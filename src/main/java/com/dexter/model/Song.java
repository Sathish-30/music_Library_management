package com.dexter.model;

import java.sql.Date;

public record Song(int songId , String songName , int publisherId , String genre , String language , Date releaseDate) {
    @Override
    public String toString() {
        return "[ "+
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", publisherId=" + publisherId +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", releaseDate=" + releaseDate +
                " ]";
    }
}
