package com.jekaizmstov.database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements Closeable {
    private static final String URL = "jdbc:mysql://localhost:3306/weather_app?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    private static DBConnection dbConnection;
    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DBConnection getConnection() throws SQLException {
        if (dbConnection == null){
            return new DBConnection();
        }
        return dbConnection;
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
