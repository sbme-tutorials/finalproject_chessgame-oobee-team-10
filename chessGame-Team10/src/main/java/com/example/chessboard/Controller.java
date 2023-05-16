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


    GameDataModel newGameData;

    public void initialize() {
        newGameData = new GameDataModel();
        boardtheme = newGameData.getTheme();
        Game game = new Game(boardGrid, "Sayed",newGameData);
        bKilled.getChildren().add(game.bKilledPiecesBox);

        wKilled.getChildren().add(game.wKilledPiecesBox);
        game.blackTimer.setTimerLabel(bTimerLabel);
        game.whiteTimer.setTimerLabel(wTimerLabel);

        game.whiteTimer.startTimer();
        game.blackTimer.startTimer();
        game.whiteTimer.setTimerColor("white");
        game.blackTimer.setTimerColor("black");


    }





}
