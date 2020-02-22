package com.jekaizmstov.controllers;

import com.jekaizmstov.Main;
import com.jekaizmstov.clothese.*;
import com.jekaizmstov.database.DBConnection;
import com.jekaizmstov.weather.TodayWeather;
import com.jekaizmstov.weather.Weather;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class ClotheController {
    @FXML
    private AnchorPane parent;

    @FXML
    private Button closeButton;

    private List<Clothe> list;
    private Umbrella umbrella;

    private List<Clothe> getLook(TodayWeather weather){
        List<Clothe> list = new ArrayList<>();
        list.add(new Hat(weather.getAvarageTemp()));
        list.add(new Jacket(weather.getAvarageTemp()));
        list.add(new Pant(weather.getAvarageTemp()));
        list.add(new Scarf(weather.getAvarageTemp()));
        list.add(new Shirt(weather.getAvarageTemp()));
        list.add(new Shoes(weather.getAvarageTemp()));
        list.add(new Socks(weather.getAvarageTemp()));
        try(DBConnection con = DBConnection.getConnection()){
            for (Clothe c: list){
                c.getPathAndDescription(con, "tables");
            }

        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Проблемы с работой БД, повторите попытку позже");
            alert.showAndWait();
            System.exit(0);
        }
        this.umbrella = new Umbrella(MainController.todayWeather.isRain());

        return list;
    }

    @FXML
    void initialize() {
       list = getLook(MainController.tomorrowWeather);
       try{
           for (Clothe c : list){
               System.out.println(c.getUrlForImage());
               System.out.println(c.getDescription());
           }
           System.out.println(umbrella.getUrlForImage());
       }catch (Exception ex){

       }
    }



    @FXML
    private Button backButton;
    public void onCloseButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
        Main.stage.show();
    }

    //make this window dragable
    private double xOffSet = 0;
    private double yOffSet = 0;
    public void parentOnMousePressed(MouseEvent mouseEvent) {
        xOffSet = mouseEvent.getSceneX();
        yOffSet = mouseEvent.getSceneY();
    }
    public void parentOnMouseDragged(MouseEvent mouseEvent) {
        MainController.clotheStage.setX(mouseEvent.getScreenX() - xOffSet);
        MainController.clotheStage.setY(mouseEvent.getScreenY() - yOffSet);
        MainController.clotheStage.setOpacity(0.8f);
    }
    public void parentOnMouseReleased(MouseEvent mouseEvent) {
        MainController.clotheStage.setOpacity(1.0f);
    }
    public void parentDragDone(DragEvent dragEvent) {
        MainController.clotheStage.setOpacity(1.0f);
    }
}
