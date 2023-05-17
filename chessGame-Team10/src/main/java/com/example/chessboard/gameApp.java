package com.example.chessboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class gameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(gameApp.class.getResource("hello-view.fxml"));
        Scene gameScene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Hello!");
        stage.setScene(gameScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}