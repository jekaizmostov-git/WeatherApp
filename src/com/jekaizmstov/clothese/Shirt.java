package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.SQLException;

public class Shirt extends Clothe {
    public Shirt(double value) {
        super(value, 5);
    }

    public void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        super.getPathAndDescription(connection, "shirts");
    }

    public static void main(String[] args) {
        Shirt shirt = new Shirt(14);
        System.out.println(shirt.getTemp());
    }
}
