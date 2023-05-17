
package com.example.chessboard;

import java.util.ArrayList;
import java.util.Objects;

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

        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)){
                    continue;
                }
                else
                    possibleMoves.add(move);

//                if (Is_square_attacked(move)){
//                    getPossibleMoves().remove(move);
                //}
            }
        }
    }



    //Check Detection Function
    public void isCheck ()
    {
        for (Square square :Game.testboard.squares)
        {   Piece piece = (Piece) square.getChildren().get(0);
            if (!Objects.equals(piece.color, this.color))
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


    //CheckMate Function
//    public boolean Is_square_attacked(String move) {
//        for (Square square : Game.testboard.squares) {
//            if (square.occupied) {
//                {
//                    Piece piece = (Piece) square.getChildren().get(0);
//                    piece.getAllPossibleMoves();
//                    if (!Objects.equals(piece.color, this.color)) {
//                        if (piece.unpossibleMoves.contains(move))
//                            return true;
//                    }
//                }
//            } else if (!square.occupied) {
//                Piece piece = (Piece) square.getChildren().get(0);
//                piece.getAllPossibleMoves();
//                if (!Objects.equals(piece.color, this.color)) {
//                    if (piece.possibleMoves.contains(move))
//                        return true;
//                }
//            }
//        }
//        return false;
//    }

}
