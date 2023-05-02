package com.example.chess.board;


import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Rook";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        String name;

        this.possibleMoves = new ArrayList<>();

        for(int i=x-1; i>=0; i--){
            name = "Square" + i + y;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x+1; i<8; i++){
            name = "Square" + i + y;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int j=y-1; j>=0; j--){
            name = "Square" + x + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int j=y+1; j<8; j++){
            name = "Square" + x + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }


    }
}

