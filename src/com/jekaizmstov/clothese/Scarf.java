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

    public static void main(String[] args) {
        Scarf hat = new Scarf(3.4);
        System.out.println(hat.getTemp());
        try(DBConnection connection = DBConnection.getConnection()){
            hat.getPathAndDescription(connection,"scarfs");
            System.out.println(hat.getTemp());
            System.out.println(hat.getDescription());
            System.out.println(hat.getPath());
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }
    }
}
