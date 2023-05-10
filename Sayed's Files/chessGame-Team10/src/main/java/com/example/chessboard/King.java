package com.example.chessboard;

import java.util.ArrayList;

public class King extends Piece{
    public King(String color, int posX, int posY) {
        super(color, posX, posY);;
        this.type = "King";
        setImage();
    }


    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        if (color.equals("black")&&(x==0||x!=7)&&y==0)
        {
            hasMoved=true;
        } else if (color.equals("white")&&(x==0||x==7)&&y==7) {
            hasMoved=true;
        }

        moves.add("Square" + (x) + (y-1));
        moves.add("Square" + (x+1) + (y-1));
        moves.add("Square" + (x+1) + (y));
        moves.add("Square" + (x+1) + (y+1));
        moves.add("Square" + (x) + (y+1));
        moves.add("Square" + (x-1) + (y+1));
        moves.add("Square" + (x-1) + (y));
        moves.add("Square" + (x-1) + (y-1));



        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;

                possibleMoves.add(move);

            }
        }


    }
}
