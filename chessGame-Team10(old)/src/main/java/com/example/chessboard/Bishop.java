package com.example.chessboard;

import java.util.ArrayList;
public class Bishop extends Piece{
    public Bishop(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Bishop";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        String name;
        this.possibleMoves = new ArrayList<>();

        for(int i=x-1, j=y+1; i>=0 && j<8; i--, j++){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }

        for(int i=x+1, j=y+1; i<8 && j<8; i++, j++){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x+1, j=y-1; i<8 && j>=0; i++, j--){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }

        for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

            possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }
    }
}
