package com.example.chessboard;

public class GameDataModel {
    public String theme="Demo";
    private String playerOneName;
    private String playerTwoName;
    private String background;
    private String pieceTheme;
    private int gameTimer= 15;
    private String currentPlayer= "white";

    public void setCurrentPlayer(String player){
        this.currentPlayer= player;
    }
    public String getCurrentPlayer(){
        return currentPlayer;
    }


    public void setGameTimer(String s){
        this.gameTimer= Integer.parseInt(s);
    }

    public int getGameTimer(){
        return this.gameTimer;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public void setPlayerOneName(String name) {
        this.playerOneName = name;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerTwoName(String name) {
        this.playerTwoName = name;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackground() {
        return background;
    }

    public void setPieceTheme(String theme) {
        this.pieceTheme = theme;
    }

    public String getPieceTheme() {
        return pieceTheme;
    }
}