package com.jekaizmstov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.SQLException;

import com.jekaizmstov.database.DBConnection;

public class Main extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("Моя погода :)");
        primaryStage.setScene(new Scene(root, 672, 476));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        this.stage = primaryStage;
    }


    public static void main(String[] args) {

        launch(args);
    }
}
