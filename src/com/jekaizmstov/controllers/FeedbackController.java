package com.jekaizmstov.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FeedbackController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeFeedbackButton;

    @FXML
    private Button sendFeedBackButton;

    @FXML
    void onCloseFeedbackButton(ActionEvent event) {
        Stage stage = (Stage)closeFeedbackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onSendFeedBackButton(ActionEvent event) {
        Stage stage = (Stage)sendFeedBackButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ваш отзыв успешно отправлен!");
        alert.showAndWait();
        stage.close();
    }

    @FXML
    void initialize() {

    }

}
