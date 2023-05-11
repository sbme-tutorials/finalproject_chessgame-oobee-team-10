package com.example.chessboard;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    GridPane chessBoard;

    public void initialize(){
        Game game = new Game(chessBoard, "Sayed");
    }
}
