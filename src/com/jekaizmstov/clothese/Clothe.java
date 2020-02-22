package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Clothe {
    String description;
    int temp;
    String path;
    File file;

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
    public void getPathAndDescription(DBConnection connection, String tableName) throws SQLException {
        String query = "SELECT * FROM "+tableName+" WHERE temp=?;";
        PreparedStatement ps = connection.getPreparedStatement(query);
        ps.setInt(1, this.temp);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            path = rs.getString("path");
            description = rs.getString("description");
            file = new File(rs.getString("path"));
        }

    }

    public String getUrlForImage() throws MalformedURLException {
        if (file != null){
            return file.toURI().toURL().toString();
        }
        return null;
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
                File file = new File(rs.getString("path"));
                System.out.println("File.exist - "+file.exists());
                String urlForImage = file.toURI().toURL().toString();
                System.out.println(urlForImage);
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
                ", path='" + path + '\'' + file.toString();

    }
}
