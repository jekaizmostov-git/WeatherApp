package com.jekaizmstov.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TodayWeather {
    private static  final String KEY = "00:00";


    List<Weather> list = new ArrayList<Weather>();

    protected TodayWeather(JSONObject obj, int start, int end) throws Exception{
        JSONArray arr = obj.getJSONArray("list");
        for (int i = start; i < end; i++){
            JSONObject justObj = (JSONObject)arr.get(i);
            list.add(new Weather(justObj));
            if (list.get(list.size() - 1).getDateText().equalsIgnoreCase(KEY) && i != 0){
                StarterEnder.start = i;
                StarterEnder.end = i + 8;
            }
        }
    }

    public TodayWeather(JSONObject obj) throws Exception{
        JSONArray arr = obj.getJSONArray("list");
        for (int i = 0; i < 8; i++){
            JSONObject justObj = (JSONObject)arr.get(i);
            list.add(new Weather(justObj));
            if (list.get(list.size() - 1).getDateText().equalsIgnoreCase(KEY) && i != 0){
                StarterEnder.start = i;
                StarterEnder.end = i + 8;
            }
        }
    }

    public void print(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        System.out.println("Сегодня "+ formattedDateTime);
        for(Weather w: list){
            System.out.println(w);
        }
    }

    public double getAvarageTemp(){
        return list.stream().mapToDouble(w -> w.getTemp()).average().getAsDouble();
    }

    public boolean isRain(){
         return   list.stream().
                filter(w -> w.getNaturePhenomena().equalsIgnoreCase("дождь") ||
                        w.getNaturePhenomena().equalsIgnoreCase("местами небольшой дождь")).count() != 0;
    }
}
