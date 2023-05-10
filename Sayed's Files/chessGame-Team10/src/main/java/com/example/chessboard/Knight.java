package com.example.chessboard;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
        this.unpossibleMoves = new ArrayList<>();

        moves.add("Square" + (x+3) + (y+2));
        moves.add("Square" + (x+3) + (y-2));
        moves.add("Square" + (x+2) + (y+3));
        moves.add("Square" + (x-2) + (y+3));
        moves.add("Square" + (x-3) + (y+2));
        moves.add("Square" + (x-3) + (y-2));
        moves.add("Square" + (x+2) + (y-3));
        moves.add("Square" + (x-2) + (y-3));


        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) {
                    unpossibleMoves.add(move);
                    showAllUnPossibleMoves(true);
                    continue;
                }
                else
                    possibleMoves.add(move);

            }
        }


    }

}
