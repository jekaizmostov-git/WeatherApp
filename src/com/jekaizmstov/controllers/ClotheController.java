package com.jekaizmstov.controllers;

import com.jekaizmstov.Main;
import com.jekaizmstov.clothese.*;
import com.jekaizmstov.database.DBConnection;
import com.jekaizmstov.weather.TodayWeather;
import com.jekaizmstov.weather.TomorrowWeather;
import com.jekaizmstov.weather.Weather;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.WeakHashMap;

public class ClotheController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private ImageView shoes;

    @FXML
    private ImageView jacket;

    @FXML
    private ImageView shirt;

    @FXML
    private ImageView pant;

    @FXML
    private ImageView socks;

    @FXML
    private ImageView scarf;

    @FXML
    private ImageView umbrella;

    @FXML
    private ImageView hat;

    @FXML
    private Button closeButton;

    @FXML
    private Button todayCLotheButton;

    @FXML
    private Button tomorrowClotheButton;

    @FXML
    private Button backButton;

    @FXML
    private Text description;

    private List<Clothe> list;
    private Umbrella umbrellaObject;

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
       this.umbrellaObject = new Umbrella(weather.isRain());


        return list;
    }

    @FXML
    void initialize() {
       setInf(MainController.todayWeather);
    }

    private void setInf(TodayWeather w){
        list = getLook(w);

        hat.setImage(null);
        jacket.setImage(null);
        pant.setImage(null);
        scarf.setImage(null);
        shirt.setImage(null);
        shoes.setImage(null);
        socks.setImage(null);
        umbrella.setImage(null);

        setImageAndToolTip(hat, list.get(0));
        setImageAndToolTip(jacket, list.get(1));
        setImageAndToolTip(pant, list.get(2));
        setImageAndToolTip(scarf, list.get(3));
        setImageAndToolTip(shirt, list.get(4));
        setImageAndToolTip(shoes, list.get(5));
        setImageAndToolTip(socks, list.get(6));
        setImageAndToolTip(umbrella, umbrellaObject);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM");
        if (w instanceof TomorrowWeather){
            description.setText("Примерный мужской гардеров на "
                    +LocalDateTime.now().plusDays(1).format(formatter) + "(завтра)");
            return;
        }
        description.setText("Примерный мужской гардеров на "+
                LocalDateTime.now().format(formatter)+ "(сегодня)");
    }

    private void setImageAndToolTip(ImageView iv, Clothe cl) {
        try{
            if (cl.getUrlForImage() != null)
                iv.setImage(new Image(cl.getUrlForImage()));
            Tooltip.install(iv, new Tooltip(cl.getDescription()));
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }

    }
    private void setImageAndToolTip(ImageView iv, Umbrella um) {
        try{
            if (um.getUrlForImage() != null)
                iv.setImage(new Image(um.getUrlForImage()));
            Tooltip.install(iv, new Tooltip(um.getDescription()));
        }catch (Exception ex){
            ex.printStackTrace(System.out);
        }
    }

    public void onTomorrowClotheClick(ActionEvent actionEvent) {
        setInf(MainController.tomorrowWeather);
    }

    public void onTodayClotheClick(ActionEvent actionEvent) {
       setInf(MainController.todayWeather);
    }


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
