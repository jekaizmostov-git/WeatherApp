package com.jekaizmstov.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jekaizmstov.weather.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

public class MainController {

//
//    @FXML
//    private LineChart<?, ?> testLineChart;

    @FXML
    private Button feedBackButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeButton;

    @FXML
    private LineChart<?, ?> chart; //delete

    @FXML
    private CategoryAxis X_chart; //delete

    @FXML
    private NumberAxis Y_chart; //delete




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
          //  System.out.println(weather);

            TodayWeather todayWeather = new TodayWeather(apiFull);
            TomorrowWeather tomorrowWeather = new TomorrowWeather(apiFull);
            OnFiveDaysWeather onFiveDaysWeather = new OnFiveDaysWeather(apiFull);

          //  System.out.println("WEATHER: "+weather);
          //  todayWeather.print();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //delete this section in future
        try{
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data("00.00", 5));
            series.getData().add(new XYChart.Data("03.00", 3));
            series.getData().add(new XYChart.Data("06.00", 2));
            series.getData().add(new XYChart.Data("09.00", 5));
            series.getData().add(new XYChart.Data("12.00", 6));
            series.getData().add(new XYChart.Data("15.00", 3));
            series.getData().add(new XYChart.Data("18.00", 1));
            series.getData().add(new XYChart.Data("21.00", 2));
            chart.getData().add(series);
        }catch (Exception ex){
            System.out.println(ex);
        }



    }

    public void onFeedBackButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/feedback.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
