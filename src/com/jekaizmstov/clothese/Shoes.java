package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.SQLException;

public class Shoes extends Clothe {
    public Shoes(double value) {
        super(value, 10);
    }

    public void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        super.getPathAndDescription(connection, "shoes");
    }
}
