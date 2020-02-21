package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.SQLException;

public class Jacket extends Clothe {
    public Jacket(double value) {
        super(value, 10);
    }

    public void getPathAndDescription(DBConnection connection,  String tableName) throws SQLException {
        super.getPathAndDescription(connection, "jackets");
    }




    public static void main(String[] args) {
        Jacket hat = new Jacket(3.4);
        System.out.println(hat.getTemp());
        try(DBConnection connection = DBConnection.getConnection()){
            hat.getPathAndDescription(connection,"jackets");
            System.out.println(hat.getTemp());
            System.out.println(hat.getDescription());
            System.out.println(hat.getPath());
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }
    }
}
