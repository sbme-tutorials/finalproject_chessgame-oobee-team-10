package com.example.chessboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class gameControl implements Initializable {
    public Rectangle player1;
    public Rectangle player2;
    GridPane board= new GridPane();
    private String gameTheme,playerOneName,playerTwoName;

    @FXML
    private Pane gameBoard; Game newgame = new Game(board,"Demo");

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameBoard.getChildren().add(board);

    }
}