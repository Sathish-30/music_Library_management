package com.dexter.queries;

public class UserPrivilegesQueries {
    public static String getAllSongs(){
        return "SELECT * FROM SONGS";
    }

    public static String getAllSongsByPublisher(){
        return "SELECT S.* FROM SONGS S INNER JOIN PUBLISHERS P ON S.PUBLISHERID = P.PUBLISHERID WHERE publisherName = ?";
    }

    public static String getSongByGenre(){
        return "SELECT * FROM SONGS WHERE genre = ?";
    }

    public static String getListOfGenre() {
        return "SELECT genre FROM SONGS";
    }

    public static String getSongBySongId(){
        return "SELECT songName FROM SONGS WHERE songId = ?";
    }


    public static String addSongToPlayList() {
        return "INSERT INTO PLAYLISTS VALUES(? , ? , ?)";
    }

    public static String deleteSongFromPlayList(){
        return "DELETE from PLAYLISTS WHERE playlistId = ? AND songId = ?";
    }

    public static String getSongFromPlayList(){
        return "SELECT * from PLAYLISTS WHERE playlistId = ?";
    }
    public static String getPublisherName() {
        return "SELECT publisherName FROM PUBLISHERS";
    }

    public static String checkUserHavePremiumAccount() {
        return "SELECT isSubscribed FROM USERS WHERE userId = ?";
    }

    public static String getNumberOfSongsFromDbQuery() {
        return "SELECT COUNT(*) FROM PLAYLISTS WHERE playListId = ?";
    }

    public static String checkSongPresentInPlayList() {
        return "SELECT songId from PLAYLISTS WHERE playListId = ?";
    }

//    public static String getAllSongsFromPlayList(){
//        return
//    }
}
