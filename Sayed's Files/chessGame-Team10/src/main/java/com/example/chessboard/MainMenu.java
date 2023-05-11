package com.example.chessboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu-View.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.setResizable(true);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

