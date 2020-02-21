package com.jekaizmstov.controllers;

import com.jekaizmstov.Main;
import com.jekaizmstov.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;

public class ClotheController {
    @FXML
    private AnchorPane parent;

    @FXML
    private Button closeButton;


    @FXML
    void initialize() {


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
