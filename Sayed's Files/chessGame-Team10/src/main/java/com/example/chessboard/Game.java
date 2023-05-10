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
                            if (currentPiece.color.equals(newPiece.color) && newPiece.type.equals("King") && currentPiece.type.equals("Rook")) {//this is edit
                                Castling(square);
                                clearHighlighting();
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
                        if (currentPiece.color.equals(newPiece.color) && newPiece.type.equals("King") && currentPiece.type.equals("Rook")) {//this is edit
                            clearHighlighting();
                            Castling(square);
                        } else if (currentPiece.color.equals(newPiece.color)) {
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
        deselectPiece(true);
    }


        public void Castling(Square square) {
            if (!currentPiece.possibleMoves.contains(square.name)) return;
            Piece casteledKing = (Piece) square.getChildren().get(0);   // get the king as an object
            Square initialSquare = (Square) currentPiece.getParent();   //  get the position of rook
            //  square.getChildren().remove(0);
            Square rookNewSquare = new Square(0, 7);       //    Create the new Position of the pieces  دا الجزء اللي متوقعين فيه الخطأ
            Square kingNewSquare = new Square(0, 7);       //                                  الجزء اللي متوقعين فيه الخطأ

            if (casteledKing.color.equals("white") && currentPiece.posX == 7) {    //&& currentPiece.posX==7    removed because of hasMoved
                kingNewSquare.x = 6;
                rookNewSquare.x = 5;

                //remove old position of the king and put new one.
                square.getChildren().remove(0);
                square.occupied = false;
                kingNewSquare.getChildren().add(casteledKing);
                kingNewSquare.occupied = true;

                //remove the position of the rook and put new one.
//                initialSquare.getChildren().removeAll();
                initialSquare.occupied = false;
                rookNewSquare.getChildren().add(currentPiece);
                rookNewSquare.occupied = true;

                //Add the squares to our board     //مش شغالة
                cb.squares.add(kingNewSquare);
                cb.squares.add(rookNewSquare);


                currentPiece.posX = rookNewSquare.x;
                currentPiece.posY = rookNewSquare.y;
                casteledKing.posX = kingNewSquare.x;
                casteledKing.posY = kingNewSquare.y;
                casteledKing.setPiece(new Image("D:/ChessBoard/images/pawn_b.png"));
                currentPiece.setPiece(new Image("D:/ChessBoard/images/pawn_b.png"));

            }
            else if (casteledKing.color.equals("black") && currentPiece.posX == 7) {
                kingNewSquare.x = 6;
                kingNewSquare.y = 0;
                rookNewSquare.x = 5;
                rookNewSquare.y = 0;
                square.getChildren().remove(0);
                kingNewSquare.getChildren().add(casteledKing);
                square.occupied = false;
                kingNewSquare.occupied = true;
                initialSquare.getChildren().removeAll();
                initialSquare.occupied = false;
                rookNewSquare.occupied = true;
                rookNewSquare.getChildren().add(currentPiece);
                currentPiece.posX = rookNewSquare.x;
                currentPiece.posY = rookNewSquare.y;
                casteledKing.posX = kingNewSquare.x;
                casteledKing.posY = kingNewSquare.y;
            }
            deselectPiece(true);

    }

////
////    private static boolean isCheckmate() {
////        for (Square square : Game.cb.squares) {
////            if (square.getChildren().size() > 0) {
////                Piece piece = (Piece) square.getChildren().get(0);
////                if (piece.color.equals(currentPlayer)) {
////                    for (String move : piece.possibleMoves) {
////                        Square targetSquare = piece.getSquareByName(move);
////                        // Try making the move
////                        boolean canEscapeCheck = tryMove(piece, square, targetSquare);
////                        if (canEscapeCheck) {
////                            // The player can escape check, so it's not checkmate
////                            return false;
////                        }
////                    }
////                }
////            }
////        }
////        // No valid move found, it's checkmate
////        return true;
////
////    }
////
////    private static boolean tryMove(Piece piece, Square currentSquare, Square targetSquare) {
////        // Store the current state
////        boolean currentOccupied = targetSquare.occupied;
////        Piece capturedPiece = null;
////        if (currentOccupied) {
////            capturedPiece = (Piece) targetSquare.getChildren().get(0);
////        }
////
////        // Move the piece
////        targetSquare.getChildren().add(piece);
////        targetSquare.occupied = true;
////        currentSquare.getChildren().removeAll();
////        currentSquare.occupied = false;
////        piece.posX = targetSquare.x;
////        piece.posY = targetSquare.y;
////
////        // Check if the move results in check
////        boolean inCheck = isCheck();
////
////        // Restore the previous state
////        targetSquare.getChildren().remove(piece);
////        targetSquare.occupied = currentOccupied;
////        currentSquare.getChildren().add(piece);
////        currentSquare.occupied = true;
////        piece.posX = currentSquare.x;
////        piece.posY = currentSquare.y;
////        if (currentOccupied) {
////            targetSquare.getChildren().add(capturedPiece);
////        }
////
////        // Return if the move resulted in check
////        return inCheck;
////    }
////
////    private static boolean isCheck() {
////        String opponentColor = currentPlayer.equals("white") ? "black" : "white";
////
////        // Find the current player's king square
////        Square kingSquare = findKingSquare(currentPlayer);
////
////        // Check if any opponent's piece is attacking the king
////        for (Square square : Game.cb.squares) {
////            if (square.getChildren().size() > 0) {
////                Piece piece = (Piece) square.getChildren().get(0);
////                if (piece.color.equals(opponentColor) && piece.isAttackingKing()) {
////                    return true;
////                }
////            }
////        }
////
////        return false;
////    }
////
////    private static Square findKingSquare(String playerColor) {
////        for (Square square : Game.cb.squares) {
////            if (square.getChildren().size() > 0) {
////                Piece piece = (Piece) square.getChildren().get(0);
////                if (piece.type.equals("King") && piece.color.equals(playerColor)) {
////                    return square;
////                }
////            }
////        }
////        return null;
////    }
//    public static boolean isCheck(){
//        Square KingSquare = Game.FindKing();
//
//        String name = "Square" + KingSquare.x + KingSquare.y;
////      String currentPLayer2 = currentPlayer.equals("white") ? "black" : "white";
//        Piece targetPiece = (Piece) KingSquare.getChildren().get(0);
//        for (Square square : Game.cb.squares){
//            if (square.getChildren().size() > 0){
//            Piece piece = (Piece) square.getChildren().get(0);
//            if (piece == null)
//                continue;
//            if (piece.color != targetPiece.color){
//                for (String name2 :piece.possibleMoves)
//                {
//                    if(name==name2)
//                        return true;
//                }
//            }
//        }}
//        return false;
//    }
//
//
//    public static Square FindKing(){
//        for(Square square : Game.cb.squares){
//            if (square.getChildren().size() > 0){
//                Piece piece = (Piece) square.getChildren().get(0);
//                if (square.occupied && piece.color != currentPiece.color && piece.type == "King"){
//                    return square;
//            }}
//        }

//        return null;
//    }
}