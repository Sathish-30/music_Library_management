package com.dexter.service;

import com.dexter.model.DBConnection;
import com.dexter.queries.AuthenticationQueries;

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
}
