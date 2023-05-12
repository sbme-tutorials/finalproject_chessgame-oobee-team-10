package com.example.chessboard;


import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import static com.example.chessboard.ChessBoard.clearHighlighting;



public class Game {

    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private static boolean game;
    private Square selectedSquare;

//    public static Square ZiscoSquare = Game.FindKing();
//    boolean checkmate = Game.isCheckmate(); // Call the isCheckmate() function


    public Game() {
    }

// Use the checkmate variable to perform further actions based on the result
//

    public Game(GridPane chessBoard, String theme) {
        cb = new ChessBoard(chessBoard, theme);
        currentPiece = null;
        currentPlayer = "white";
        this.game = true;
        addEventHandlers(cb.chessBoard);
    }

    public void selectSquare(Square square) {
        if (selectedSquare != null) {
            clearHighlighting();
        }
        selectedSquare = square;
        highlightAvailableMoves(square);
    }

//    public void movePiece(Square destination) {
//        // logic for moving piece from selectedSquare to destination
//        clearHighlighting();
//    }


    public void highlightAvailableMoves(Square square) {
        // logic for highlighting available moves
        // update the style of each square with setStyle method
    }

    private void addEventHandlers(GridPane chessBoard) {


        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                // Clicked on square
                if (target.toString().equals("Square")) {
                    Square square = (Square) target;
                    if (square.occupied) {
                        Piece newPiece = (Piece) square.getChildren().get(0);
                        // Selecting a new piece
                        if (currentPiece == null) {
                            currentPiece = newPiece;
                            currentPiece.getAllPossibleMoves();
                            if (!currentPiece.getColor().equals(currentPlayer)) {
                                currentPiece = null;
                                return;
                            }
                            selectPiece(game);
                        }

                        // Selecting other piece of same color || Killing a piece
                        else {
                            if (currentPiece.color.equals(newPiece.color)
                                    && newPiece.type.equals("King")
                                    && currentPiece.type.equals("Rook")
                                    &&! (newPiece.hasMoved)
                                    &&! (currentPiece.hasMoved)) {//this is edited
                                Castling(square);
                            } else if (currentPiece.color.equals(newPiece.color)) {
                                clearHighlighting();
                                deselectPiece(false);
                                currentPiece = newPiece;
                                currentPiece.getAllPossibleMoves();
                                selectPiece(game);
                            } else {
                                killPiece(square);
                            }
                        }

                    }
                    // Dropping a piece on blank square
                    else {
                        dropPiece(square);
                        clearHighlighting();
                    }
                }
                // Clicked on piece
                else {
                    Piece newPiece = (Piece) target;
                    Square square = (Square) newPiece.getParent();
                    // Selecting a new piece
                    if (currentPiece == null) {
                        clearHighlighting();
                        currentPiece = newPiece;
                        if (!currentPiece.getColor().equals(currentPlayer)) {
                            currentPiece = null;
                            return;
                        }
                        selectPiece(game);
                    }

                    // Selecting other piece of same color || Killing a piece
                    else {
                        if (currentPiece.color.equals(newPiece.color)
                                && newPiece.type.equals("King")
                                && currentPiece.type.equals("Rook")
                                &&! (newPiece.hasMoved)
                                &&! (currentPiece.hasMoved)) {//this is edited
                            Castling(square);
                        } else if (currentPiece.color.equals(newPiece.color)) {
                            clearHighlighting();
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        } else {
                            killPiece(square);
                        }
                    }

                }
            }

        });
    }


    private void selectPiece(boolean game) {
        if (!game) {
            currentPiece = null;
            return;
        }

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        currentPiece.getAllPossibleMoves();
        currentPiece.showAllPossibleMoves(true);
    }

    private void deselectPiece(boolean changePlayer) {
//        currentPiece.clearHighlighting();
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece.showAllUnPossibleMoves(false);    ////////////////////////////
        currentPiece = null;
        if (changePlayer) currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    private void dropPiece(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name)){
            return;
        }

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        PawnPromotion(square);
        deselectPiece(true);
    }

    private void killPiece(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if (killedPiece.type.equals("King")) this.game = false;

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        PawnPromotion(square);
        deselectPiece(true);
    }

    public void Castling(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name)) return;


        Piece casteledKing = (Piece) square.getChildren().get(0);
        Square initialSquare = (Square) currentPiece.getParent();
        Square rookNewSquare = null;
        Square kingNewSquare = null;

        if (casteledKing.color.equals("white")) {
            if (currentPiece.posX == 7) {
                rookNewSquare = (Square) Piece.getSquareByName("Square" + 5 + 7);
                kingNewSquare = (Square) Piece.getSquareByName("Square" + 6 + 7);
            }
            if (currentPiece.posX == 0) {
                rookNewSquare = (Square) Piece.getSquareByName("Square" + 3 + 7);
                kingNewSquare = (Square) Piece.getSquareByName("Square" + 2 + 7);
            }
            square.getChildren().remove(0);
            square.occupied = false;
            kingNewSquare.getChildren().add(casteledKing);
            kingNewSquare.occupied = true;

            initialSquare.getChildren().remove(0);
            initialSquare.occupied = false;
            rookNewSquare.getChildren().add(currentPiece);
            rookNewSquare.occupied = true;


            currentPiece.posX = rookNewSquare.x;
            currentPiece.posY = rookNewSquare.y;
            casteledKing.posX = kingNewSquare.x;
            casteledKing.posY = kingNewSquare.y;

            currentPiece.hasMoved= true;
            casteledKing.hasMoved = true;


        } else if (casteledKing.color.equals("black")) {
            if (currentPiece.posX == 7) {
                rookNewSquare = (Square) Piece.getSquareByName("Square" + 5 + 7);
                kingNewSquare = (Square) Piece.getSquareByName("Square" + 6 + 7);
            }
            if (currentPiece.posX == 0) {
                rookNewSquare = (Square) Piece.getSquareByName("Square" + 3 + 7);
                kingNewSquare = (Square) Piece.getSquareByName("Square" + 2 + 7);
            }
            rookNewSquare = (Square) Piece.getSquareByName("Square" + 5 + 0);
            kingNewSquare = (Square) Piece.getSquareByName("Square" + 6 + 0);
            square.getChildren().remove(0);
            square.occupied = false;
            kingNewSquare.getChildren().add(casteledKing);
            kingNewSquare.occupied = true;

            initialSquare.getChildren().remove(0);
            initialSquare.occupied = false;
            rookNewSquare.getChildren().add(currentPiece);
            rookNewSquare.occupied = true;


            currentPiece.posX = rookNewSquare.x;
            currentPiece.posY = rookNewSquare.y;
            casteledKing.posX = kingNewSquare.x;
            casteledKing.posY = kingNewSquare.y;
        }
        deselectPiece(true);

        currentPiece.hasMoved = true;
        casteledKing.hasMoved = true;
    }


    public void PawnPromotion(Square square) {
        Queen queen = new Queen(currentPlayer, square.x, square.y);
        if (currentPiece.type.equals("Pawn") && currentPiece.color.equals("white")) {
            if (currentPiece.posY == 0) {
                square.getChildren().remove(currentPiece);
                square.occupied = false;
                square.getChildren().add(queen);
                square.occupied = true;
            }
        } else if (currentPiece.type.equals("Pawn") && currentPiece.color.equals("black")) {
            if (currentPiece.posY == 7) {
                square.getChildren().remove(currentPiece);
                square.occupied = false;
                square.getChildren().add(queen);
                square.occupied = true;
            }
        }
    }
}