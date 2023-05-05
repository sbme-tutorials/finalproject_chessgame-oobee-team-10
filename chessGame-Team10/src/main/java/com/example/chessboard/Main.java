package com.example.chessboard;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane gridPane= new GridPane();
        Game newGame= new Game(gridPane,"Demo");
        AnchorPane anchorPane;
        anchorPane = new AnchorPane();
        anchorPane.getChildren().add(gridPane);
        anchorPane.setPrefSize(800,800);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(anchorPane, 800, 800));
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
