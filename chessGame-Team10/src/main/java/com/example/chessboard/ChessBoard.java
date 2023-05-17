package com.example.chessboard;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class ChessBoard {
    GridPane chessBoard;
    String theme;
    StatusBar statusBar = new StatusBar();

    public static ArrayList<Square> squares = new ArrayList<>();
    public ChessBoard(){}
    public ChessBoard(GridPane chessBoard, String theme){
        this.chessBoard = chessBoard;
        this.theme = theme;

        makeBoard(this.chessBoard, theme);

        //Set the Settings of the timer
        statusBar.whitePlayerAlert.setText("White Player turn");
        statusBar.blackPlayerAlert.setText("");
        statusBar.whitePlayerTimer.setText("White timer: 15:00");
        statusBar.blackPlayerTimer.setText("Black timer: 15:00");

    }


    //Design Game Board
    private void makeBoard(GridPane chessBoard, String theme){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Square square = new Square(i,j);
                square.setName("Square" + i + j);
                square.setPrefHeight(73);
                square.setPrefWidth(73);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        addPieces();
    }


    //Themes of the Board
    private void setTheme(Square square, String theme, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (theme) {
            case "Sayed" -> {
                color1 = Color.web("#210062");
                color2 = Color.web("#FFD93D");
            }
            case "Demo" -> {
                color1 = Color.web("#146C94");
                color2 = Color.web("#AFD3E2");
            }
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
            }
            case "Ahmed" -> {
                color1 = Color.web("#EBEBD0FF");
                color2 = Color.web("#779455FF");
            }
        }

        if((i+j)%2==0) {
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else {
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }


    //Add a new Piece in the Board
    public void addPiece(Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }



    //Initializing the position of Pieces
    private void addPieces(){
        for(Square square : squares){
            if(square.occupied) continue;
            if(square.y == 1){
                addPiece(square, new Pawn("black", square.x, square.y));
            }
            else if(square.y == 6){
                addPiece(square, new Pawn("white", square.x, square.y));
            }
            else if(square.y == 0){
                if(square.x == 4){
                    addPiece(square, new King("black", square.x, square.y));
                }
                if(square.x == 3){
                    addPiece(square, new Queen("black", square.x, square.y));
                }
                if(square.x == 2 || square.x == 5){
                    addPiece(square, new Bishop("black", square.x, square.y));
                }
                if(square.x == 1 || square.x == 6){
                    addPiece(square, new Knight("black", square.x, square.y));
                }
                if(square.x == 0 || square.x == 7){
                    addPiece(square, new Rook("black", square.x, square.y));
                }
            }
            else if(square.y == 7){
                if(square.x == 4){
                    addPiece(square, new King("white", square.x, square.y));
                }
                if(square.x == 3){
                    addPiece(square, new Queen("white", square.x, square.y));
                }
                if(square.x == 2 || square.x == 5){
                    addPiece(square, new Bishop("white", square.x, square.y));
                }
                if(square.x == 1 || square.x == 6){
                    addPiece(square, new Knight("white", square.x, square.y));
                }
                if(square.x == 0 || square.x == 7){
                    addPiece(square, new Rook("white", square.x, square.y));
                }
            }


        }
    }


    //Same Function of the other clearing Highlight
    public static void clearHighlighting() {
        for (Square square : squares) {
            square.setStyle("");
        }
    }


    //get the position of the opponent king to Check the Checkmate
    public  Piece getOppenentKing()
    {
        for (Square square :Game.testboard.squares)
        {
            Piece piece =(Piece) square.getChildren().get(0);
            if (piece.type.equals("King")&&!piece.color.equals(Game.currentPiece))
                return piece;
        }
        return null ;
    }

    public StatusBar getStatusBar()
    {
        return (statusBar);
    }
}
