package com.example.chess.board;


import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Pawn";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        if(color.equals("black")){
            moves.add("Square" + (x+1) + (y+1));
            moves.add("Square" + (x) + (y+1));
            moves.add("Square" + (x-1) + (y+1));
            if(posY == 1) moves.add("Square" + x + 3);
        }
        else{
            moves.add("Square" + (x) + (y-1));
            moves.add("Square" + (x+1) + (y-1));
            moves.add("Square" + (x-1) + (y-1));
            if(posY == 6) moves.add("Square" + x + 4);
        }

        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;

                if(!getSquareByName(move).occupied && getSquareByName(move).x != posX) continue;

                possibleMoves.add(move);

            }
        }
    }
}
