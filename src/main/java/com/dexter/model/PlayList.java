package com.dexter.model;

public record PlayList(int playlistId , int songId , String songName) {
    public int sum(){
        return this.songId;
    }
    @Override
    public String toString(){
        return String.format("Song id : %d  Song name : %s" , this.songId , this.songName);
    }
}
