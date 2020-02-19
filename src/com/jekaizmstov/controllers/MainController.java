package com.jekaizmstov.controllers;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

import com.jekaizmstov.Main;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private Button clotheButton;

    @FXML
    private BorderPane parent;

    @FXML
    private Button collapseButton;
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
            if (!netIsAvailable()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Отсутствует Интернет соединение, повторите попытку позже");
                alert.showAndWait();
                System.exit(0);
            }
            JSONObject apiForCurrent = API.getCurrentWetherAPI();
            JSONObject apiFull = API.getWetherAPI();
            Weather weather = new Weather(apiForCurrent);
            TodayWeather todayWeather = new TodayWeather(apiFull);
            TomorrowWeather tomorrowWeather = new TomorrowWeather(apiFull);
            OnFiveDaysWeather onFiveDaysWeather = new OnFiveDaysWeather(apiFull);




        } catch (Exception e) {
            e.printStackTrace();
        }

        //delete this section in future, for LineChart
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


        //makeStageDragable();
    }

    public static Stage stageFeedback = null;
    public void onFeedBackButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/feedback.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
        this.stageFeedback = stage;
    }

    public static Stage clotheStage = null;
    public void onClotheButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/clothe.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
        this.clotheStage = stage;

        Stage stageForClose = (Stage)clotheButton.getScene().getWindow();
        stageForClose.hide();
    }

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    public void onCollapseButtonClick(ActionEvent actionEvent) {
        Main.stage.setIconified(true);
    }


    //make window dragable
    private double xOffSet = 0;
    private double yOffSet = 0;
    public void parentOnMousePressed(MouseEvent mouseEvent) {
        xOffSet = mouseEvent.getSceneX();
        yOffSet = mouseEvent.getSceneY();
    }
    public void parentOnMouseDragged(MouseEvent mouseEvent) {
        Main.stage.setX(mouseEvent.getScreenX() - xOffSet);
        Main.stage.setY(mouseEvent.getScreenY() - yOffSet);
        Main.stage.setOpacity(0.8f);
    }
    public void parentOnMouseReleased(MouseEvent mouseEvent) {
        Main.stage.setOpacity(1.0f);
    }
    public void parentDragDone(DragEvent dragEvent) {
        Main.stage.setOpacity(1.0f);
    }

}
