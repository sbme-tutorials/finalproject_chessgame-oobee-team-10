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
        this.unpossibleMoves = new ArrayList<>();
        this.kingcheck = new ArrayList<>();

        // bishop at left edge of board
        if (x == 0) {
            name = "Square" + (x+1) + y;
            if (!getSquareByName(name).occupied || !getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                possibleMoves.add(name);
            }
        }
        // bishop at right edge of board
        else if (x == 7) {
            name = "Square" + (x-1) + y;
            if (!getSquareByName(name).occupied || !getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                possibleMoves.add(name);
            }
        }
        // bishop not at edge of board
        else {
            name = "Square" + (x+1) + y;
            if (!getSquareByName(name).occupied || !getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                possibleMoves.add(name);
            }
            name = "Square" + (x-1) + y;
            if (!getSquareByName(name).occupied || !getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                possibleMoves.add(name);
            }
        }





        //Main Diagonal //bottom left diagonal
        for(int i=x-1, j=y+1 , steps = 0; i>=0 && j<8 && steps < 3; i--, j++ , steps++){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}

            else {
                possibleMoves.add(name);
            }

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

        }
        //Boundary case
        for(int i=x-1, j=y+1, steps=0; i>=0 && j<8 && steps<3; i--, j++, steps++){
            if (i < 0 || j >= 8) {
                break; // exit loop if indices are out of bounds
            }
            name = "Square" + i + j;

            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else{
                possibleMoves.add(name);
            }

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

        }





        //Main Diagonal             //Bottom right diagonal
        for(int i=x+1, j=y+1, steps = 0; i<8 && j<8 && steps<3; i++, j++ ,steps++ ) {   //bottom right diagonal
                name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else {
                possibleMoves.add(name);
            }

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

        }
        //Boundary case
        for(int i=x+1, j=y+1, steps=0; i<8 && j<8 && steps<3; i++, j++, steps++){
            if (i >= 8 || j >= 8) {
                break; // exit loop if indices are out of bounds
            }
            name = "Square" + i + j;

            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;

        }





        //main Diagonal          //Upper Right Diagonal
        for(int i=x+1, j=y-1, steps=0; i<8 && j>=0 && steps<3; i++, j--, steps++){
            name = "Square" + i + j;
            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}

            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }
        // Boundary case
        for(int i=x+1, j=y-1, steps=0; i<8 && j>=0 && steps<3; i++, j--, steps++){
            if (i >= 8 || j < 0) {
                break; // exit loop if indices are out of bounds
            }
            name = "Square" + i + j;

            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }




        //main Diagonal     //Upper Left
        for(int i=x-1, j=y-1 , steps = 0; i>=0 && j>=0 &&steps < 3; i--, j-- , steps++){
            name = "Square" + i + j;

            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }

        //Boundary case
        for(int i=x-1, j=y-1, steps=0; i>=0 && j>=0 && steps<3; i--, j--, steps++){
            if (i < 0 || j < 0) {
                break; // exit loop if indices are out of bounds
            }
            name = "Square" + i + j;

            if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)){   //لو في قطعة موجودة و نفس النوع بتاعك يخرج
                unpossibleMoves.add(name);
                showAllUnPossibleMoves(true);
                continue;}
            else
                possibleMoves.add(name);

            if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer))
                break;
        }


    }

}
