package com.example.chessboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1030, 765);
       // stage.setTitle("Login Page!");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public void changeScene(String fxml) throws IOException{
        Parent pane=FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}