package com.dexter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnection {
    private static Connection connection;
    private DBConnection(){
         String url = "jdbc:postgresql://localhost:5432/musiclibrary";
         String user = "postgres";
         String password = "Createnumber@123";
        try {
            connection = DriverManager.getConnection(url , user , password);
            System.out.println("Database Connected");
        }catch(SQLException se){
            System.out.println("Failed to connect Database");
            se.fillInStackTrace();
        }
    }

    public static void connect(){
        // Created a singleton Class
        DBConnection db = new DBConnection();
    }
    public static Optional<PreparedStatement> getPreparedStatement(String query){
        try {
            return Optional.of(connection.prepareStatement(query));
        }catch (SQLException sqlException){
            sqlException.fillInStackTrace();
        }
        return Optional.empty();
    }

    public static void closeConnection() {
        try {
             connection.close();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
