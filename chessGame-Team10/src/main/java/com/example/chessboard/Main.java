package com.example.chessboard;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("chessgameteam10/sample.fxml"));




        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(parent, 800, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
