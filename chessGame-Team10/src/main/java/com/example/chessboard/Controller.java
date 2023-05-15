package com.example.chessboard;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Controller {

    public HBox bKilled;
    public Pane boardContainer;
    public GridPane boardGrid;
    public HBox wKilled;
    public Pane bPlayerContainer;
    public Pane bTimer;
    public Pane wPlayerContainer;
    public Pane wTimer;
    public Label wTimerLabel;
    public Label bTimerLabel;
    private String boardtheme;

    private ChessTimer whiteTimer;
    private ChessTimer blackTimer;
    GameDataModel newGameData;

    public void initialize() {
        whiteTimer = new ChessTimer(wTimerLabel);
        blackTimer = new ChessTimer(bTimerLabel);
        whiteTimer.startTimer();
        newGameData = new GameDataModel();
        boardtheme = newGameData.getTheme();
        Game game = new Game(boardGrid, "Sayed");

        if (Game.currentPlayer.equals("")) {
            whiteTimer.stopTimer();
        }
    }


    public ChessTimer getBlackTimer() {
        return blackTimer;
    }

    public ChessTimer getWhiteTimer() {
        return whiteTimer;
    }
}
