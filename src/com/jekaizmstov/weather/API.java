package com.jekaizmstov.weather;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    private static String yourCity = "Hrodna,BY";
  //  private static String yourCity = "Bissau";


    public static void setCity(String city) {
        yourCity = city;
    }

    private static final String URL_STRING_FOR_CURRENT_WETHER ="http://api.openweathermap.org/data/2.5/weather?q="+yourCity+"&units=metric&appid=8ac1008e95214c5edc42c9ec43e47239";
    private static final String URL_STRING = "http://api.openweathermap.org/data/2.5/forecast?q="+yourCity+"&units=metric&appid=8ac1008e95214c5edc42c9ec43e47239";

    private static StringBuilder getAPI(String urlString) throws Exception{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode = "+responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine = "";
        StringBuilder response = new StringBuilder();
        while  ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    public static JSONObject getCurrentWetherAPI() throws Exception{
        StringBuilder response = getAPI(URL_STRING_FOR_CURRENT_WETHER);
        return new JSONObject(response.toString());
    }

    public static JSONObject getWetherAPI() throws Exception{
        StringBuilder response = getAPI(URL_STRING);
        return new JSONObject(response.toString());
    }

}
