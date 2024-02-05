package com.dexter.service;

import com.dexter.model.DBConnection;
import com.dexter.model.Publisher;
import com.dexter.model.User;
import com.dexter.queries.AuthenticationQueries;
import com.dexter.queries.DataQuery;
import com.dexter.queries.PublisherPrivilegesQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PublisherService {

    public static boolean addPublisherToDb(String pName, String emailId, String password) {
        String query = AuthenticationQueries.publisherSignUp();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , pName);
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

    public static boolean checkPublisherCredentials(String emailId, String password) {
        String query = AuthenticationQueries.publisherSignIn();
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
        String query = AuthenticationQueries.publisherInDb();
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

    public static Optional<Publisher> getPublisherByEmailIdFromDb(String emailId) {
        String query = DataQuery.getPublisherByEmailId();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , emailId);
                ResultSet rs = preparedStatement.executeQuery();
                Publisher publisher = new Publisher();
                while(rs.next()){
                    publisher.setPublisherId(rs.getInt(1));
                    publisher.setPublisherName(rs.getString(2));
                    publisher.setEmailId(rs.getString(3));
                    publisher.setPassword(rs.getString(4));
                }
                return Optional.of(publisher);
            }catch (SQLException se){
                se.fillInStackTrace();
            }
        }
        return Optional.empty();
    }

    public static boolean addSongToDB(String sName, int publisherId, String genre, String language) {
        String query = PublisherPrivilegesQueries.addSong();
        Optional<PreparedStatement> getOptionalPreparedStatement = DBConnection.getPreparedStatement(query);
        if(getOptionalPreparedStatement.isPresent()){
            try (PreparedStatement preparedStatement = getOptionalPreparedStatement.get()){
                preparedStatement.setString(1 , sName);
                preparedStatement.setInt(2 , publisherId);
                preparedStatement.setString(3 , genre);
                preparedStatement.setString(4 , language);
                preparedStatement.execute();
                return true;
            }catch(SQLException se){
                se.fillInStackTrace();
            }
        }
        return false;
    }
}
