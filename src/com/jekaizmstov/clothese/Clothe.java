package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Clothe {
    String description;
    int temp;
    String path;

    public Clothe(double value,int round){
        this.temp = rounding(value, round);
    }

   private int rounding(double value, int round){
        if (value > 0)
            return  (int)(Math.round(value) - ((int)Math.round(value) % round));
        else{
            return  (int)(Math.round(value) - (round + (int)Math.round(value) % round));
        }
    }
    void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        String query = "SELECT * FROM "+tableName+" WHERE temp=?;";
        PreparedStatement ps = connection.getPreparedStatement(query);
        ps.setInt(1, this.temp);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            path = rs.getString("path");
            description = rs.getString("description");
        }

    }

    public static void main(String[] args) {
        try(DBConnection con = DBConnection.getConnection()){
            String tableName = "hats";
            String query = "SELECT * FROM "+tableName+" WHERE temp=?;";
            PreparedStatement ps = con.getPreparedStatement(query);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("path"));
                System.out.println(rs.getString("description"));
            }
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }
    }


    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "Clothe{" +
                "description='" + description + '\'' +
                ", temp=" + temp +
                ", path='" + path + '\'' +
                '}';
    }
}
