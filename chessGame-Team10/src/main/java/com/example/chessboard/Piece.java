package com.example.chessboard;


import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Piece extends ImageView {
    ArrayList<String> mayCheck;
    String type;
    static String piecDesign="original";
    String color;
    int posX, posY;
    boolean hasMoved = false;
    ArrayList<String> unpossibleMoves;
    ArrayList<String> possibleMoves;
    public static void setPieceDesign(String s){
        piecDesign= s;
    }



    public Piece(String color, int posX, int posY){
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        addEventHandler();
    }

    public String getColor(){
        return this.color;
    }

    public void setPiece(Image image){
        this.setImage(image);
        this.setFitWidth(40);
        this.setFitHeight(60);
    }


    //Set iamge of each Piece
    public void setImage(){
        if (this.type == "Pawn")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/bp.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wp.png"));

        else if (this.type == "Bishop")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/bb.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wb.png"));

        else if (this.type == "King")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/bkn.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wkn.png"));

        else if (this.type == "Queen")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/bq.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wq.png"));

        else if (this.type == "Rook")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/br.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wr.png"));

        else if (this.type == "Knight")
            if (this.color == "black")
                this.setPiece(new Image("File:images/"+piecDesign+"/bk.png"));
            else
                this.setPiece(new Image("File:images/"+piecDesign+"/wk.png"));

    }


    // When we click on a square include piece => get all possible moves of this piece
    private void addEventHandler(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });


    }

    //get all possible moves of each piece - logic of this fuction depends on the type of Piece
    public void getAllPossibleMoves() {

    }

    //Take this Possible moves and then display it on screen with green color
    public void showAllPossibleMoves(boolean val){
        clearHighlighting();
        Glow glow = new Glow();
        if(val){
          glow.setLevel(0.25);

            // Set the background color of a block to green
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(glow);
                square.setStyle("-fx-background-color: green;");
                Piece piece = getPieceByName(move);
                if(piece == null) continue;
                if(piece.type.equals("King")){
                    square.setEffect(glow);
                    square.setStyle("-fx-background-color: blue;");
                }
                else{
                    square.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.2))));
                }
            }
        }
        else{
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(null);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }


    //Same idea but in Unpossible moves
    public void showAllUnPossibleMoves(boolean val) {
        clearHighlighting();
        Glow glow = new Glow();
        if(val){
            glow.setLevel(0.25);
            // Set the background color of a block to red
            for(String move : unpossibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(glow);
                square.setStyle("-fx-background-color: red;");
                Piece piece = getPieceByName(move);
                if(piece == null) continue;
            }
        }
        else{
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(null);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public ArrayList<String> getPossibleMoves(){
        return possibleMoves;
    }


    //get the position of Square as a String
    public static Square getSquareByName(String name){
        for(Square square : Game.cb.squares){
            if(square.name.equals(name)){
                return square;
            }
        }

        return null;
    }

    //get the name of the piece -> we will use it in many functions and Conditions
    public Piece getPieceByName(String name){
        for(Square square : Game.cb.squares){
            if(square.getChildren().size() == 0) continue;

            if(square.name.equals(name))
                return (Piece) square.getChildren().get(0);

        }
        return null;
    }

    //Clear the possible and unpossible moves after drop , deselect or kill a Piece
    public void clearHighlighting() {
        for (String move : this.possibleMoves) {
            Square square = this.getSquareByName(move);
            square.setEffect(null);
            square.setStyle(""); // دي معناها ان الللون هيبقي شفاف او فاضي
        }

    }


    public static void setPiecesforTest()
    {
        for (Square square:Game.testboard.squares)
        {   if (square.occupied) {
            Piece piece = (Piece) square.getChildren().get(0);
            piece.posX = square.x;
            piece.posY = square.y;
        }else continue;
        }


    }

    @Override
    public String toString() {
        return this.color + " " + this.type;
    }


}

//      square.setBorder(new Border(new BorderStroke(Color.DARKRED,
//       BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));