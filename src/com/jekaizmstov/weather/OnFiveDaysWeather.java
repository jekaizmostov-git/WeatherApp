package com.jekaizmstov.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OnFiveDaysWeather implements Iterable<Weather> {
    private int[] sequence = {1,5,9,13,17,21,25,29,33,37};
    List<Weather> wetherList = new ArrayList<>();

    public OnFiveDaysWeather(JSONObject jsonObject) throws Exception {
        JSONArray list = jsonObject.getJSONArray("list");
        int length = sequence.length;
        for (int i = 0; i < length; i++){
            JSONObject wether = (JSONObject)list.get(sequence[i]);
            wetherList.add(new Weather(wether));
        }


    }

    public void print(){
        String time = null;
        LocalDate date = LocalDate.now();
        date = date.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM");
        for (int i = 0; i < wetherList.size(); i++){
            if (i % 2 == 0){
                date = date.plusDays(1);
                String formattedDateTime = date.format(formatter);
                System.out.println(formattedDateTime);
                time = "Средняя температура ночью ";
            }else{
                time = "Средняя температура днем ";
            }
            System.out.println(time + wetherList.get(i).toString());
        }
    }

    @Override
    public Iterator<Weather> iterator() {
        return new Iterator<Weather>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return (wetherList.size()>index)?true:false;
            }

            @Override
            public Weather next() {
                return wetherList.get(index++);
            }
        };
    }

    public Weather getItem(int index){
        if (index >= 0 && index < wetherList.size())
            return wetherList.get(index);
        return null;
    }
}
