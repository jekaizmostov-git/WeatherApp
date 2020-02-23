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
        translate();
    }

    public Weather() throws Exception {
        this(API.getCurrentWetherAPI());
    }

    @Override
    public String toString() {
        return this.getDateText()+": Температура:"+this.getTemp()+" °C, "+this.getNaturePhenomena()+"," +
                "\nощущается как: "+this.getFillsLike()+" °C," +
                " скорость ветра:"+this.getWindSpeed()+" м/с, " +
                "\nвлажность:"+this.getHumidity()+" %," +
                " давление:"+this.getPressure()+" ГПА";
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

    private void translate(){
        switch (this.naturePhenomena){
            case "overcast clouds": this.naturePhenomena = "пасмурно"; break;
            case "broken clouds": this.naturePhenomena = "облачность"; break;
            case "scattered clouds": this.naturePhenomena = "рассеяные облака"; break;
            case "few clouds": this.naturePhenomena = "мало облаков"; break;
            case "clear sky": this.naturePhenomena = "чистое небо"; break;
            case "light rain": this.naturePhenomena = "местами небольшой дождь"; break;
            case "moderate rain": this.naturePhenomena = "дождь"; break;
            case "light snow": this.naturePhenomena = "местами небольшой снег"; break;
            case "snow": this.naturePhenomena = "снег"; break;
            default: break;
        }
    }
}
