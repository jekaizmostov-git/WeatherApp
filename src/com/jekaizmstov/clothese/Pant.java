package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.SQLException;

public class Pant extends Clothe {
    public Pant(double value) {
        super(value, 10);
    }

    public void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        super.getPathAndDescription(connection, "pants");
    }
}
