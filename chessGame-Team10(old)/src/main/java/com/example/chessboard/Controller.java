package com.example.chessboard;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    GridPane chessBoard;

    public void initialize(){

        // Themes are Demo, Coral, Dusk, Wheat, Marine, Emerald, Sandcastle
        //and alot of themes coming soon!
        Game game = new Game(chessBoard, "Demo");

    }
}
