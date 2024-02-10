package com.dexter;

import com.dexter.model.DBConnection;
import com.dexter.views.Main;


public class App {
    public static void main( String[] args ){
        DBConnection.connect();
        Main.landingPage();
        DBConnection.closeConnection();
    }
}

