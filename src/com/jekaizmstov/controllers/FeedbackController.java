package com.jekaizmstov.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jekaizmstov.Main;
import com.jekaizmstov.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FeedbackController{

    @FXML
    private AnchorPane parent;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeFeedbackButton;

    @FXML
    private Button sendFeedBackButton;

    @FXML
    private TextField name;

    @FXML
    private TextArea text;

    @FXML
    void onCloseFeedbackButton(ActionEvent event) {
        Stage stage = (Stage)closeFeedbackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onSendFeedBackButton(ActionEvent event) {
        Stage stage = (Stage)sendFeedBackButton.getScene().getWindow();
        String name = this.name.getText();
        String text = this.text.getText();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yy HH:mm");
        String formatDate = date.format(formatter);

        if (name.equalsIgnoreCase("") || text.equalsIgnoreCase("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Для отправки отзыва вам необходимо заполнить все поля!");
            alert.showAndWait();
            return;
        }

        text = text.trim();
        name = name.trim();

        try(DBConnection connection = DBConnection.getConnection()){
            String query = "INSERT INTO `weather_app`.`feedbacks` (time, name, text) VALUES (?,?,?);";
            PreparedStatement ps = connection.getPreparedStatement(query);
            ps.setString(1, formatDate);
            ps.setString(2, name);
            ps.setString(3, text);
            ps.execute();
            ps.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ваш отзыв успешно отправлен!");
            alert.showAndWait();
            stage.close();
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Проблемы с работой БД, повторите попытку позже");
            alert.showAndWait();
            stage.close();
        }


    }

    @FXML
    void initialize() {

    }

    //make this window dragable
    private double xOffSet = 0;
    private double yOffSet = 0;
    public void parentOnMousePressed(MouseEvent mouseEvent) {
        xOffSet = mouseEvent.getSceneX();
        yOffSet = mouseEvent.getSceneY();
    }
    public void parentOnMouseDragged(MouseEvent mouseEvent) {
        MainController.stageFeedback.setX(mouseEvent.getScreenX() - xOffSet);
        MainController.stageFeedback.setY(mouseEvent.getScreenY() - yOffSet);
        MainController.stageFeedback.setOpacity(0.8f);
    }
    public void parentOnMouseReleased(MouseEvent mouseEvent) {
        MainController.stageFeedback.setOpacity(1.0f);
    }
    public void parentDragDone(DragEvent dragEvent) {
        MainController.stageFeedback.setOpacity(1.0f);
    }


}
