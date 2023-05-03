package com.example.chess.board;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class mainGame extends Application {

    @Override
    public void start(Stage stage) throws IOException {



        Rectangle rec = new Rectangle();
        rec.fillProperty();
        GridPane gridPane= new GridPane();
        gridPane.getChildren().add(rec);

        Scene scene = new Scene(gridPane, 800, 800);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
