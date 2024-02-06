package com.dexter.service;

import com.dexter.model.DBConnection;
import com.dexter.model.PlayList;
import com.dexter.model.Song;
import com.dexter.model.User;
import com.dexter.queries.AuthenticationQueries;
import com.dexter.queries.DataQuery;
import com.dexter.queries.UserPrivilegesQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    public static boolean addUserToDb(String uName, String emailId, String password) {
        String query = AuthenticationQueries.userSignUp();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , uName);
                preparedStatement.setString(2 , emailId);
                preparedStatement.setString(3 , password);
                preparedStatement.execute();
                return true;
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean CheckUserCredentials(String emailId, String password) {
        String query = AuthenticationQueries.userSignIn();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , emailId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                String dbPassword = rs.getString(1);
                return dbPassword.equals(password);
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean checkUserAlreadyInDb(String emailId) {
        String query = AuthenticationQueries.userInDb();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , emailId);
                ResultSet rs = preparedStatement.executeQuery();
                return rs.next();
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static Optional<User> getUserFromDb(String emailId) {
        String query = DataQuery.getUserByEmailId();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , emailId);
                ResultSet rs = preparedStatement.executeQuery();
                User user = new User();
                while(rs.next()){
                    user.setUserId(rs.getInt(1));
                    user.setUserName(rs.getString(2));
                    user.setEmailId(rs.getString(3));
                    user.setPassword(rs.getString(4));
                    user.setPlayListId(rs.getInt(5));
                }
                return Optional.of(user);
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return Optional.empty();
    }

    public static void getAllSongsFromDB() {
        String query = UserPrivilegesQueries.getAllSongs();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                ResultSet rs = preparedStatement.executeQuery();
                System.out.print("---------------------------------------------------------------------------- \n");
                while(rs.next()){
                    Song song = new Song(rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getString(4) , rs.getString(5) , rs.getDate(6));
                    System.out.println(song);
                }
                System.out.println("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }

    public static void getAllPublisherSongsFromDB(String pName) {
        String query = UserPrivilegesQueries.getAllSongsByPublisher();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , pName);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.print("---------------------------------------------------------------------------- \n");
                while(rs.next()){
                    Song song = new Song(rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getString(4) , rs.getString(5) , rs.getDate(6));
                    System.out.println(song);
                }
                System.out.println("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }

    public static void getAllSongsFromDbByGenre(String genre) {
        String query = UserPrivilegesQueries.getSongByGenre();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , genre);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("List of Genre :");
                System.out.print("---------------------------------------------------------------------------- \n");
                while(rs.next()){
                    Song song = new Song(rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getString(4) , rs.getString(5) , rs.getDate(6));
                    System.out.println(song);
                }
                System.out.print("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }

    public static void getListOfGenreFromDb() {
        String query = UserPrivilegesQueries.getListOfGenre();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                ResultSet rs = preparedStatement.executeQuery();
                System.out.print("---------------------------------------------------------------------------- \n");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                System.out.print("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }

    public static void addSongsToPlayList(int songId, int playListId) {
        Optional<String> optionalSongName = UserService.getSongNameBySongId(songId);
        if(optionalSongName.isPresent()){
            String songName = optionalSongName.get();
            if(!songName.isEmpty()){
                String query = UserPrivilegesQueries.addSongToPlayList();
                Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
                if(getOptionalPreparedStatement.isPresent()){
                    try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                        preparedStatement.setInt(1 , playListId);
                        preparedStatement.setInt(2 , songId);
                        preparedStatement.setString(3 , songName);
                        preparedStatement.execute();
                        System.out.print("---------------------------------------------------------------------------- \n");
                        System.out.println("Song added to the playlist......");
                        System.out.print("---------------------------------------------------------------------------- \n");
                    }catch (SQLException se){
                        se.fillInStackTrace();
                    }
                }
            }else{
                System.out.println("Unable to find the song");
            }
        }
    }

    // The below function is used by the above userService
    private static Optional<String> getSongNameBySongId(int id) {
        String query = UserPrivilegesQueries.getSongBySongId();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setInt(1 , id);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return Optional.of(rs.getString(1));
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return Optional.empty();
    }

    public static boolean deleteSongFromPlayList(int songId, int playListId) {
        String query = UserPrivilegesQueries.deleteSongFromPlayList();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setInt(1 , playListId);
                preparedStatement.setInt(2 , songId);
                preparedStatement.execute();
                return true;
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return false;
    }

    public static void getSongsFromThePlayList(int playListId) {
        String query = UserPrivilegesQueries.getSongFromPlayList();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setInt(1 , playListId);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.print("---------------------------------------------------------------------------- \n");
                boolean status = false;
                while(rs.next()){
                    status = true;
                    PlayList playListSongs = new PlayList(rs.getInt(1) , rs.getInt(1) , rs.getString(3));
                    System.out.println(playListSongs);
                }
                if(!status) System.out.println("No Songs in the playlist");
                System.out.print("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }

    public static void getListOfPublishers() {
        String query = UserPrivilegesQueries.getPublisherName();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()) {
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("Publishers Name : ");
                System.out.print("---------------------------------------------------------------------------- \n");
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
                System.out.print("---------------------------------------------------------------------------- \n");
            } catch (SQLException se) {
                se.fillInStackTrace();
            }
        }
    }
}
