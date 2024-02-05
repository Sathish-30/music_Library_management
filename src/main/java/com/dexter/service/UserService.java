package com.dexter.service;

import com.dexter.model.DBConnection;
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
                System.out.print("---------------------------------------------------------------------------- \n");
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
                System.out.print("---------------------------------------------------------------------------- \n");
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
    }
}
