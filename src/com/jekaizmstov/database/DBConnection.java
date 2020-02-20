package com.jekaizmstov.database;


import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

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

    public Statement getStatement() throws SQLException {
        if (connection != null){
            return  connection.createStatement();
        }else{
            return getConnection().getStatement();
        }
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection != null){
            return  connection.prepareStatement(query);
        }else{
            return getConnection().getPreparedStatement(query);
        }
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
