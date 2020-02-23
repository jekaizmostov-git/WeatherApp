package com.jekaizmstov.controllers;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jekaizmstov.Main;
import com.jekaizmstov.clothese.Hat;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

public class MainController {
    @FXML
    private Button updateButton;


    @FXML
    private BorderPane parent;

    @FXML
    private Text currentTitleText;

    @FXML
    private ImageView currentIcon;

    @FXML
    private Text currentTempAndNP;

    @FXML
    private Text currentFeelsLikeText;

    @FXML
    private Text currentHumidityText;

    @FXML
    private Text currentPressureText;

    @FXML
    private Text curentWindSpeedText;

    @FXML
    private LineChart<?, ?> todayLineChart;

    @FXML
    private CategoryAxis todayLineChart_X;

    @FXML
    private NumberAxis todayLineChart_Y;

    @FXML
    private ImageView todayIcon1;

    @FXML
    private ImageView todayIcon2;

    @FXML
    private ImageView todayIcon3;

    @FXML
    private ImageView todayIcon4;

    @FXML
    private ImageView todayIcon5;

    @FXML
    private ImageView todayIcon6;

    @FXML
    private ImageView todayIcon7;

    @FXML
    private ImageView todayIcon8;

    @FXML
    private Text todayDescription;

    @FXML
    private LineChart<?, ?> tomorrowLineChart;

    @FXML
    private CategoryAxis tomorrowLineChart_X;

    @FXML
    private NumberAxis tomorrowLineChart_Y;

    @FXML
    private ImageView tomorrowIcon1;

    @FXML
    private ImageView tomorrowIcon2;

    @FXML
    private ImageView tomorrowIcon3;

    @FXML
    private ImageView tomorrowIcon4;

    @FXML
    private ImageView tomorrowIcon5;

    @FXML
    private ImageView tomorrowIcon6;

    @FXML
    private ImageView tomorrowIcon7;

    @FXML
    private ImageView tomorrowIcon8;

    @FXML
    private Text tomorrowDescription;

    @FXML
    private Button clotheButton;

    @FXML
    private Button feedBackButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button collapseButton;







    @FXML
    void onCloseButtonClick(ActionEvent event) {
        System.exit(0);
    }

    public static JSONObject apiForCurrent;
    public static JSONObject apiFull;
    public static Weather weather;
    public static TodayWeather todayWeather;
    public static TomorrowWeather tomorrowWeather;
    public static OnFiveDaysWeather onFiveDaysWeather;
    private static DateTimeFormatter formatter;

    private void setInf() throws Exception {
        //check Internet Connection!
        if (!netIsAvailable()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Отсутствует Интернет соединение, повторите попытку позже");
            alert.showAndWait();
            System.exit(0);
        }
        //get Inf about weather
        apiForCurrent = API.getCurrentWetherAPI();
        apiFull = API.getWetherAPI();
        this.weather = new Weather(apiForCurrent);
        this.todayWeather = new TodayWeather(apiFull);
        this.tomorrowWeather = new TomorrowWeather(apiFull);
        this.onFiveDaysWeather = new OnFiveDaysWeather(apiFull);


        //set content for currentWeather
        File fileForCurrentIcon = new File("src\\com\\jekaizmstov\\view\\icons\\"+weather.getIcon()+".png");
        currentIcon.setImage(new Image(fileForCurrentIcon.toURI().toURL().toString()));

        formatter = DateTimeFormatter.ofPattern("d-MMMM HH:mm");
        currentTitleText.setText(API.getYourCity() + " "+ LocalDateTime.now().format(formatter));
        currentTempAndNP.setText("t: "+weather.getTemp()+" °C, "+weather.getNaturePhenomena());
        currentFeelsLikeText.setText("Ощущается как: "+weather.getFillsLike()+" °C");
        curentWindSpeedText.setText("Скорость ветра: "+weather.getWindSpeed()+" м/с");
        currentHumidityText.setText("Влажность: "+weather.getHumidity()+"%");
        currentPressureText.setText("Давление: "+weather.getPressure()+" ГПА");

        formatter = DateTimeFormatter.ofPattern("d-MMMM");
        //set content for today weather
        todayLineChart.setTitle(API.getYourCity() + " "+ LocalDateTime.now().format(formatter));
        todayDescription.setText(todayWeather.getItem(0).toString());
        setIcon(todayIcon1, todayWeather.getItem(0));
        setIcon(todayIcon2, todayWeather.getItem(1));
        setIcon(todayIcon3, todayWeather.getItem(2));
        setIcon(todayIcon4, todayWeather.getItem(3));
        setIcon(todayIcon5, todayWeather.getItem(4));
        setIcon(todayIcon6, todayWeather.getItem(5));
        setIcon(todayIcon7, todayWeather.getItem(6));
        setIcon(todayIcon8, todayWeather.getItem(7));
        drawLineChart(todayWeather);

        //set content for tomorrow weather
        tomorrowLineChart.setTitle(API.getYourCity() + " "+ LocalDateTime.now().plusDays(1).format(formatter));
        tomorrowDescription.setText(tomorrowWeather.getItem(0).toString());
        setIcon(tomorrowIcon1, tomorrowWeather.getItem(0));
        setIcon(tomorrowIcon2, tomorrowWeather.getItem(1));
        setIcon(tomorrowIcon3, tomorrowWeather.getItem(2));
        setIcon(tomorrowIcon4, tomorrowWeather.getItem(3));
        setIcon(tomorrowIcon5, tomorrowWeather.getItem(4));
        setIcon(tomorrowIcon6, tomorrowWeather.getItem(5));
        setIcon(tomorrowIcon7, tomorrowWeather.getItem(6));
        setIcon(tomorrowIcon8, tomorrowWeather.getItem(7));
        drawLineChart(tomorrowWeather);

    }

    private void drawLineChart(TodayWeather t){


        XYChart.Series series = new XYChart.Series();
        for(Weather w: t){
            series.getData().add(new XYChart.Data(w.getDateText(), w.getTemp()));
        }
        if (t instanceof TomorrowWeather) {
            tomorrowLineChart.getData().addAll(series);
        }else{
            todayLineChart.getData().addAll(series);
        }
    }

    private void setIcon(ImageView iv, Weather w) throws Exception {
        File fileIcon = new File("src\\com\\jekaizmstov\\view\\icons\\"+w.getIcon()+".png");
        iv.setImage(new Image(fileIcon.toURI().toURL().toString()));
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        try {
            todayLineChart.getData().clear();
            tomorrowLineChart.getData().clear();
            setInf();
            System.out.println("Data get successfuly!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        try {
            setInf();
            System.out.println("Data get successfuly!");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    //SHITCODE :((((((((
    public void onTodayIcon1Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(0).toString());
    }

    public void onTodayIcon2Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(1).toString());
    }

    public void onTodayIcon3Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(2).toString());
    }

    public void onTodayIcon4Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(3).toString());
    }

    public void onTodayIcon5Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(4).toString());
    }

    public void onTodayIcon6Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(5).toString());
    }

    public void onTodayIcon7Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(6).toString());
    }

    public void onTodayIcon8Click(MouseEvent mouseEvent) {
        todayDescription.setText(todayWeather.getItem(7).toString());
    }



    //SHITCODE N2 :(((((((
    public void onTomorrowIcon1Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(0).toString());
    }

    public void onTomorrowIcon2Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(1).toString());
    }

    public void onTomorrowIcon3Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(2).toString());
    }

    public void onTomorrowIcon4Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(3).toString());
    }

    public void onTomorrowIcon5Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(4).toString());
    }

    public void onTomorrowIcon6Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(5).toString());
    }

    public void onTomorrowIcon7Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(6).toString());
    }

    public void onTomorrowIcon8Click(MouseEvent mouseEvent) {
        tomorrowDescription.setText(tomorrowWeather.getItem(7).toString());
    }


}
