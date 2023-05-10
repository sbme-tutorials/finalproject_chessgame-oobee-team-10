package com.example.chessboard;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pawn extends Piece {
    boolean flag = false;
    public Pawn(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Pawn";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        String name;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        if ((color == "white" && y == 0) || (color == "black" && y == 7)) {
            if (color.equals("black"))
                this.setPiece(new Image("D:/ChessBoard/images/queen_b.png"));
            else
                this.setPiece(new Image("D:/ChessBoard/images/queen_w.png"));
            flag = true;
        }
        if(flag){

            for(int i=x-1 ; i>=0 ; i--){
                name = "Square" + i + y;
                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }
                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int i=x+1; i<8; i++){

                name = "Square" + i + y;
                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int j=y-1; j>=0; j--){

                name = "Square" + x + j;
                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
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
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int i=x-1, j=y+1; i>=0 && j<8; i--, j++){
                name = "Square" + i + j;

                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int i=x+1, j=y+1; i<8 && j<8; i++, j++){
                name = "Square" + i + j;
                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int i=x+1, j=y-1; i<8 && j>=0; i++, j--){
                name = "Square" + i + j;

                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

            for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--){
                name = "Square" + i + j;

                if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) {
                    showAllUnPossibleMoves(true);
                    break;
                }

                else
                    possibleMoves.add(name);

                if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
            }

        }

        else {

            if (color.equals("black")) {
                moves.add("Square" + (x + 1) + (y + 1));
                moves.add("Square" + (x) + (y + 1));
                moves.add("Square" + (x - 1) + (y + 1));
                if (posY == 1) moves.add("Square" + x + 3);
            } else {
                moves.add("Square" + (x) + (y - 1));
                moves.add("Square" + (x + 1) + (y - 1));
                moves.add("Square" + (x - 1) + (y - 1));
                if (posY == 6) moves.add("Square" + x + 4);
            }

            for (String move : moves) {
                if (getSquareByName(move) != null) {
                    if (getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)){
                        showAllUnPossibleMoves(true);
                    }

                    if (!getSquareByName(move).occupied && getSquareByName(move).y != posY && getSquareByName(move).x != posX)
                        continue;
                    possibleMoves.add(move);

                }
            }
        }
    }
}


