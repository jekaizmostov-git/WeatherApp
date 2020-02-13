package com.jekaizmstov.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jekaizmstov.weather.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import org.json.JSONObject;

public class MainController {

//
//    @FXML
//    private LineChart<?, ?> testLineChart;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeButton;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private CategoryAxis X_chart;

    @FXML
    private NumberAxis Y_chart;


    @FXML
    void onCloseButtonClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        try {
            JSONObject apiForCurrent = API.getCurrentWetherAPI();
            JSONObject apiFull = API.getWetherAPI();

            Weather weather = new Weather(apiForCurrent);
            System.out.println(weather);

            TodayWeather todayWeather = new TodayWeather(apiFull);
            TomorrowWeather tomorrowWeather = new TomorrowWeather(apiFull);
            OnFiveDaysWeather onFiveDaysWeather = new OnFiveDaysWeather(apiFull);

            onFiveDaysWeather.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        XYChart.Series series = new XYChart.Series();
//        series.getData().add(1,1);
//        series.getData().add(2,2);
//        series.getData().add(3,3);
//        series.getData().add(4,4);
//        series.getData().add(5,5);
//        series.getData().add(6,6);
//        series.getData().add(7,7);
//        series.getData().add(8,8);
//
//        testLineChart.getData().add(series);

    }
}
