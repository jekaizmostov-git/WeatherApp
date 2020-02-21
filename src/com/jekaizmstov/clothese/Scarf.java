package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.SQLException;

public class Scarf extends Clothe{
    public Scarf(double value) {
        super(value, 10);
    }

    public void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        super.getPathAndDescription(connection, "scarfs");
    }
}
