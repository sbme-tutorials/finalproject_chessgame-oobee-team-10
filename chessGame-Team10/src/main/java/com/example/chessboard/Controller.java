package com.example.chessboard;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView bg;
    private String boardtheme;


    GameDataModel newGameData;

    public void initialize() {
        GridPane.setMargin( boardContainer,new Insets(25));
        bg.setImage(new Image("File:images/backGrounds/stonebg.png"));

        newGameData = new GameDataModel();
        boardtheme = newGameData.getTheme();
        Game game = new Game(boardGrid);
        bKilled.getChildren().add(game.bKilledPiecesBox);

        wKilled.getChildren().add(game.wKilledPiecesBox);
        game.blackTimer.setTimerLabel(bTimerLabel);
        game.whiteTimer.setTimerLabel(wTimerLabel);
        game.whiteTimer.setTimerColor("white");
        game.blackTimer.setTimerColor("black");

        game.whiteTimer.startTimer();
        game.blackTimer.startTimer();




    }





}
