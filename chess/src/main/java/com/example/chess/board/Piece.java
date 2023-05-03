package com.example.chess.board;


import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Piece extends ImageView {
    String type;
    String color;
    int posX, posY;
    ArrayList<String> possibleMoves;

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
    }

    public void setImage(){
        String path = "G:/AHMED/SBME 1st Year/OOP/finalproject_chessgame-oobee-team-10/chess/images";
        if (this.type == "Pawn")
            if (this.color == "black")
//              this.setPiece(new Image("images/pieces/" + this .color + "" + this.type + ".png"));
                this.setPiece(new Image(path +"/pawn_b.png"));
            else
                this.setPiece(new Image(path +"/pawn_w.png"));

        else if (this.type == "Bishop")
            if (this.color == "black")
                this.setPiece(new Image(path +"/bishop_b.png"));
            else
                this.setPiece(new Image(path +"/bishop_w.png"));

        else if (this.type == "King")
            if (this.color == "black")
                this.setPiece(new Image(path +"/king_b.png"));
            else
                this.setPiece(new Image(path +"/king_w.png"));

        else if (this.type == "Queen")
            if (this.color == "black")
                this.setPiece(new Image(path +"/queen_b.png"));
            else
                this.setPiece(new Image(path +"/queen_w.png"));

        else if (this.type == "Rook")
            if (this.color == "black")
                this.setPiece(new Image(path +"/rook_b.png"));
            else
                this.setPiece(new Image(path +"/rook_w.png"));

        else if (this.type == "Knight")
            if (this.color == "black")
                this.setPiece(new Image(path +"/knight_b.png"));
            else
                this.setPiece(new Image(path +"/knight_w.png"));

    }

    private void addEventHandler(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });


    }

    public void getAllPossibleMoves() {}

    public void showAllPossibleMoves(boolean val){
        if(val){
            Glow glow = new Glow();
            glow.setLevel(0.3);
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(glow);

                Piece piece = getPieceByName(move);
                if(piece == null) continue;
                if(piece.type.equals("King")){
                    square.setBorder(new Border(new BorderStroke(Color.DARKRED,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
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

    public Square getSquareByName(String name){
        for(Square square : Game.cb.squares){
            if(square.name.equals(name)){
                return square;
            }
        }

        return null;
    }

    public Piece getPieceByName(String name){
        for(Square square : Game.cb.squares){
            if(square.getChildren().size() == 0) continue;

            if(square.name.equals(name))
                return (Piece) square.getChildren().get(0);

        }
        return null;
    }

    @Override
    public String toString() {
        return this.color + " " + this.type;
    }

}
