package com.dexter.model;

public record PlayList(int playlistId , int songId , String songName) {
    @Override
    public String toString(){
        return String.format("Song id : %d  Song name : %s" , this.songId , this.songName);
    }
}
