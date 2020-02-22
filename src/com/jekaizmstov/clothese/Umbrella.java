package com.jekaizmstov.clothese;

import com.jekaizmstov.database.DBConnection;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;


public class Umbrella{
    File file;
    public Umbrella(boolean flag){
        if (flag){
            file = new File("src/clothe/umbrellas/0.jpg");
        }
    }

    public String getUrlForImage() throws MalformedURLException {
        if (file != null){
            return file.toURI().toURL().toString();
        }
        return null;
    }

    public String getDescription(){
        return "Сегодня с большой вероятностью будет дождь, возьмите зонт, чтобы не промокнуть!";
    }
}

