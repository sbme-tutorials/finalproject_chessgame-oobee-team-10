
package com.example.chessboard;

import java.util.ArrayList;

public class King extends Piece{

    boolean isCheck =false;


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

        moves.add("Square" + (x) + (y-1));
        moves.add("Square" + (x+1) + (y-1));
        moves.add("Square" + (x+1) + (y));
        moves.add("Square" + (x+1) + (y+1));
        moves.add("Square" + (x) + (y+1));
        moves.add("Square" + (x-1) + (y+1));
        moves.add("Square" + (x-1) + (y));
        moves.add("Square" + (x-1) + (y-1));
      /*  if (!hasMoved)
        {
            moves.add("Square" + (x+2) + (y));
            moves.add("Square" + (x-2) + (y));
        }*/


        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;
                possibleMoves.add(move);
            }
        }
    }
    public void isCheck ()
    {
        for (Square square :Game.cb.squares)
        {   Piece piece = (Piece) square.getChildren().get(0);
            if (piece.color != this.color)
            {
                for (String move:piece.possibleMoves)
                {   Square newSquare=(Square) this.getParent();
                    if (move.equals(newSquare.name))
                    {
                        isCheck=true;
                    }
                }
            }
        }


    }



}
