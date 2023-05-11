package com.example.chessboard;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Rook";
        setImage();
    }
    public void ismoved()
    {
        if (color.equals("black")&&(posX!=0||posX!=7)&&posY!=0)
        {
            hasMoved=true;
        } else if (color.equals("white")&&(posX!=0||posX!=7)&&posY!=7) {
            hasMoved=true;
        }
    }
    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        String name;

        this.possibleMoves = new ArrayList<>();
        this.unpossibleMoves = new ArrayList<>();



        for(int i=x-1; i>=0; i--){
            name = "Square" + i + y;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)&&getPieceByName(name).type.equals("King")&&!hasMoved)
            {
                possibleMoves.add(name);
            }

            else if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                break;
            }
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }





        for(int i=x+1; i<8; i++){
            name = "Square" + i + y;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)&&getPieceByName(name).type.equals("King")&&!hasMoved)
            {
                possibleMoves.add(name);
            }

            else if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                break;
            }
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }





        for(int j=y-1; j>=0; j--){
            name = "Square" + x + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                break;
            }
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }





        for(int j=y+1; j<8; j++){
            name = "Square" + x + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                break;
            }
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

        }


    }
}