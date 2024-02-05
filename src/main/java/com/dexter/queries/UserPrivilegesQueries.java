package com.dexter.queries;

public class UserPrivilegesQueries {
    public static String getAllSongs(){
        return "SELECT * FROM SONGS";
    }

    public static String getAllSongsByPublisher(){
        return "SELECT S.* FROM SONGS S INNER JOIN PUBLISHERS P ON S.PUBLISHERID = P.PUBLISHERID WHERE publisherName = ?";
    }
}
