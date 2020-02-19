package com.jekaizmstov.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jekaizmstov.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
