package com.example.chess.board;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;

public class mainGame extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        GridPane chessBoard = new GridPane();


        // Themes are Demo, Coral, Dusk, Wheat, Marine, Emerald, Sandcastle
            //and alot of themes coming soon!

        int numCols =8;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            chessBoard.getColumnConstraints().add(colConst);
        }

        int numRows=8;
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            chessBoard.getRowConstraints().add(rowConst);
        }



        Scene scene = new Scene( new Game(chessBoard, "Demo"), 800, 800);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
