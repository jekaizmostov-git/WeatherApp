package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        double temp = 4.75;
        List<Clothe> list = new ArrayList<>();
        list.add(new Hat(temp));
        list.add(new Jacket(temp));
        list.add(new Pant(temp));
        list.add(new Scarf(temp));
        list.add(new Shirt(temp));
        list.add(new Shoes(temp));
        list.add(new Socks(temp));
        try(DBConnection connection  = DBConnection.getConnection()){
            for (Clothe c: list){
                c.getPathAndDescription(connection, "tables");
              //  System.out.println(c.getTemp());
             //   System.out.println(c);
             //   System.out.println(c.getUrlForImage());
            }
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }

    }
}
