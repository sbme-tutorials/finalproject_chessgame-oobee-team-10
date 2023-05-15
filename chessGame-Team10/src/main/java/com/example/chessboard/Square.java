package com.example.chessboard;

import javafx.scene.layout.StackPane;
public class Square extends StackPane {

    int x,y;
    //occupied mean that this Square contain a Piece
    boolean occupied;
    public String name;
    public Square(){}
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
        return "Square";
    }
    public void setName(String name){
        this.name = name;
    }

    private void setSquareColor() {
        String color = (x + y) % 2 == 0 ? "#ffffff" : "#000000";
        this.setStyle("-fx-background-color: " + color + ";");
    }
}


