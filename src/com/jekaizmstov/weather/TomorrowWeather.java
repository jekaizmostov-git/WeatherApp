package com.jekaizmstov.weather;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TomorrowWeather extends TodayWeather {

    public TomorrowWeather(JSONObject obj) throws Exception {
        super(obj,StarterEnder.start, StarterEnder.end);
    }

    @Override
    public void print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM");
        String formattedDateTime = LocalDateTime.now().plusDays(1).format(formatter); // "1986-04-08 12:30"
        System.out.println("Сегодня "+ formattedDateTime);
        for(Weather w: list){
            System.out.println(w);
        }
    }
}
