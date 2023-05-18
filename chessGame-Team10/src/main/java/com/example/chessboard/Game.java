package com.example.chessboard;


import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.example.chessboard.ChessBoard.clearHighlighting;
import static com.example.chessboard.Piece.getSquareByName;


public class Game {
    GameDataModel gameData;
    public static ChessBoard testboard;
    public HBox wKilledPiecesBox = new HBox(15);
    public HBox bKilledPiecesBox = new HBox(15); // container for killed pieces

    int killedW= 0;
    int killedB=0;

    private ImageView[] killedPieces = new ImageView[32]; // array to hold image views of killed pieces
    public ChessTimer whiteTimer = new ChessTimer();

    public ChessTimer blackTimer = new ChessTimer();

    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private static boolean game;
    private Square selectedSquare;



    //Default Constructor
    public Game() {
    }

 String theme =DashFX.boardtheme;
    public Game(GridPane chessBoard) {
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

    //Unused
    public void highlightAvailableMoves(Square square) {
        // logic for highlighting available moves
        // update the style of each square with setStyle method
    }


    //Event Handler
    private void addEventHandlers(GridPane chessBoard) {


        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                // Clicked on Square
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
                                    && !(newPiece.hasMoved)
                                    && !(currentPiece.hasMoved)) {//this is edited
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
                                && !(newPiece.hasMoved)
                                && !(currentPiece.hasMoved)) {//this is edited
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
        clearHighlighting();
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
        clearHighlighting();
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece.showAllUnPossibleMoves(false);
        currentPiece = null;
        if (changePlayer) {
            currentPlayer = currentPlayer.equals("white") ? "black" : "white";
        }

    }

    private void dropPiece(Square square) {
        clearHighlighting();
        if (!currentPiece.possibleMoves.contains(square.name)) {

            return;
        }

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        InCheck(square , true);
        promotePawn(square);
        whiteTimer.pauseAndPlay(currentPlayer);
        blackTimer.pauseAndPlay(currentPlayer);
        deselectPiece(true);
        gameData.setCurrentPlayer(currentPlayer);





    }


    private void killPiece(Square square) {
        clearHighlighting();
        if (!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        InCheck(square , true);
        promotePawn(square);
        deselectPiece(true);
        if (killedPiece.type.equals("King")) {this.game = false;
        whiteTimer.timeline.stop();
        blackTimer.timeline.stop();
        }

        if(killedPiece.color.equals("white")) {
            Piece killed = killedPiece;
            killed.setFitHeight(50);
            killed.setFitWidth(40);
            wKilledPiecesBox.getChildren().add(killed);
        }
        else
        if(killedPiece.color.equals("black")){
            Piece killed = killedPiece;
            killed.setFitHeight(50);
            killed.setFitWidth(40);
            bKilledPiecesBox.getChildren().add(killed);

        }
    }


    public void Castling(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name)) return;

        Piece casteledKing = (Piece) square.getChildren().get(0);
        Square initialSquare = (Square) currentPiece.getParent();
        Square rookNewSquare = null;
        Square kingNewSquare = null;

        if (casteledKing.color.equals("white")) {
            if (currentPiece.posX == 7) {
                rookNewSquare = (Square) getSquareByName("Square" + 5 + 7);
                kingNewSquare = (Square) getSquareByName("Square" + 6 + 7);
            }
            if (currentPiece.posX == 0) {
                rookNewSquare = (Square) getSquareByName("Square" + 3 + 7);
                kingNewSquare = (Square) getSquareByName("Square" + 2 + 7);
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

            currentPiece.hasMoved = true;
            casteledKing.hasMoved = true;


        } else if (casteledKing.color.equals("black")) {
            if (currentPiece.posX == 7) {
                rookNewSquare = (Square) getSquareByName("Square" + 5 + 7);
                kingNewSquare = (Square) getSquareByName("Square" + 6 + 7);
            }
            if (currentPiece.posX == 0) {
                rookNewSquare = (Square) getSquareByName("Square" + 3 + 7);
                kingNewSquare = (Square) getSquareByName("Square" + 2 + 7);
            }
            rookNewSquare = (Square) getSquareByName("Square" + 5 + 0);
            kingNewSquare = (Square) getSquareByName("Square" + 6 + 0);
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


    public void promotePawn(Square square) {

        if (currentPiece.type.equals("Pawn") && (currentPiece.posY == 0 || currentPiece.posY == 7)) {

            //Parts to be Promoted
            Queen queen = new Queen(currentPlayer, square.x, square.y);
            Bishop bishop = new Bishop(currentPlayer, square.x, square.y);
            Knight knight = new Knight(currentPlayer, square.x, square.y);
            Rook rook = new Rook(currentPlayer, square.x, square.y);

            // Create a callback to handle the promotion type selection
            PromotionCallback callback = piece -> {
                switch (piece) {
                    case "Queen":
                        square.getChildren().remove(currentPiece);
                        square.occupied = false;
                        square.getChildren().add(queen);
                        square.occupied = true;
                        break;
                    case "Bishop":
                        square.getChildren().remove(currentPiece);
                        square.occupied = false;
                        square.getChildren().add(bishop);
                        square.occupied = true;
                        break;
                    case "Knight":
                        square.getChildren().remove(currentPiece);
                        square.occupied = false;
                        square.getChildren().add(knight);
                        square.occupied = true;
                        break;
                    case "Rook":
                        square.getChildren().remove(currentPiece);
                        square.occupied = false;
                        square.getChildren().add(rook);
                        square.occupied = true;
                        break;
                    default:
                        break;
                }
            };

            //Remove Pawn Piece
            square.getChildren().remove(currentPiece);

            // Create the promotion window and pass the callback
            Stage stage = new Stage();
            PawnPromotionWindow pawnPromotionWindow = new PawnPromotionWindow(callback);
            pawnPromotionWindow.start(stage);
        }
    }



    public void InCheck(Square square ,boolean val){
        clearHighlighting();
        Glow glow = new Glow();
        if (val){
            glow.setLevel(1);
            Piece piece = (Piece) square.getChildren().get(0);
            piece.getAllPossibleMoves();
            for(String move : piece.possibleMoves){
                Square square_pointer = getSquareByName(move);
                if (square_pointer.occupied){
                    Piece checked = (Piece) square_pointer.getChildren().get(0);
                    if (checked.type.equals("King") && checked.color != piece.color){
                        System.out.println("Game.InCheck");
//                      Checkmate(square);
                        square_pointer.setEffect(glow);
                        square_pointer.setBackgroundColor(Color.BLUE);
                        Stage stage = new Stage();
                        CheckingWindow checking = new CheckingWindow();
                        checking.start(stage);// Set the background color to red
                        break;
                    }
            }}
        }
    }


//    public void testCheck()
//    {
//        Piece OppenentKing = cb.getOppenentKing();
//        ((King) OppenentKing).isCheck();
//        if (((King) OppenentKing).isCheck)
//        {   testboard = new ChessBoard();//  I'm not sure if things related to board will need somethig to access or not
//            testboard.squares.addAll(cb.squares);//  might be problem here because I'm not sure if this enough to set pieces in their places or will we even need that
//            Piece.setPiecesforTest(); //
//            for (Square square: testboard.squares)
//            {   if (square.occupied) {
//
//
//                Piece piece = (Piece) square.getChildren().get(0);
//                if (piece.color.equals(OppenentKing.color)) {
//                    for (String move :piece.possibleMoves)
//                    {
//                        Square newSquare=Piece.getSquareByName(move);
//                        square.getChildren().remove(piece);
//                        square.occupied=false;
//                        newSquare.getChildren().add(piece);
//                        newSquare.occupied=true;
//                        piece.posX= newSquare.x;
//                        piece.posY= newSquare.y;
//                        ((King) OppenentKing).isCheck();
//                        if (!((King) OppenentKing).isCheck) {
//                            deselectPiece(true );
//                            break;
//                        }
//                        // restore the piece
//                        newSquare.getChildren().remove(piece);
//                        square.occupied=false;
//                        square.getChildren().add(piece);
//                        square.occupied=true;
//                        piece.posX= square.x;
//                        piece.posY= square.y;
//
//                    }
//                }
//            }else continue;
//            }
//            if (((King) OppenentKing).isCheck)
//                game=false;
//        }
//    }


    public  void stopGame(boolean flag){
        this.game = false;
    }

    interface PromotionCallback {
        void onPromotionSelected(String piece);
    }
}
