package com.jekaizmstov.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;

public class Weather {
    private double temp;
    private double fillsLike;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private String naturePhenomena;
    private String dateText;
    private String icon;

    public Weather(JSONObject jsonObject) throws Exception {
        JSONObject currentWether = new JSONObject(jsonObject.getJSONObject("main").toString());
        try{
            this.dateText = jsonObject.getString("dt_txt").substring(11, 16);
        }catch(Exception ex){
            this.dateText = LocalTime.now().toString().substring(0, 5);
        }
        JSONObject currentWindSpeed = new JSONObject(jsonObject.getJSONObject("wind").toString());
        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        this.naturePhenomena = jsonArray.getJSONObject(0).getString("description");
        this.icon = jsonArray.getJSONObject(0).getString("icon");
        this.temp = currentWether.getDouble("temp");
        this.fillsLike = currentWether.getDouble("feels_like");
        this.humidity = currentWether.getInt("humidity");
        this.pressure = currentWether.getInt("pressure");
        this.windSpeed = currentWindSpeed.getDouble("speed");
    }

    public Weather() throws Exception {
        this(API.getCurrentWetherAPI());
    }

    @Override
    public String toString() {
        return "CurrentWether{" +
                ", dateText='" + dateText + '\'' +
                "temp=" + temp +
                ", fillsLike=" + fillsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", naturePhenomena='" + naturePhenomena + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public double getTemp() {
        return temp;
    }

    public double getFillsLike() {
        return fillsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getNaturePhenomena() {
        return naturePhenomena;
    }

    public String getIcon() {
        return icon;
    }

    public String getDateText() {
        return dateText;
    }
}
