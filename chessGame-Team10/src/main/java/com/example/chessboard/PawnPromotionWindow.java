package com.example.chessboard;//package com.example.chessboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class PawnPromotionWindow extends Application {
    private final Game.PromotionCallback callback;
    public static String type = null;
    public PawnPromotionWindow(Game.PromotionCallback callback) {
        this.callback = callback;
    }

    public void start(Stage primaryStage) {
        // Create the window and handle the promotion type selection as before
        // Create a grid pane to hold the buttons and label
        // Create a grid pane to hold the buttons and label
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create a label with the promotion message
        Label promotionLabel1 = new Label("Congratulations you get a promotion :)");

        // Create four buttons for the promotion types
        Button queenButton = new Button("Queen");
        Button knightButton = new Button("Knight");
        Button bishopButton = new Button("Bishop");
        Button rookButton = new Button("Rook");

        // Add the label and buttons to the grid pane
        gridPane.add(promotionLabel1, 0, 0, 4, 1);
        gridPane.add(queenButton, 0, 1);
        gridPane.add(knightButton, 1, 1);
        gridPane.add(bishopButton, 2, 1);
        gridPane.add(rookButton, 3, 1);

        // Create a scene with the grid pane
        Scene scene = new Scene(gridPane, 400, 100);

        // Set the stage title and scene
        primaryStage.setTitle("Pawn Promotion");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set the action for each button to close the window and return the promotion type
        queenButton.setOnAction(event -> {
            primaryStage.close();
            handlePromotionSetType("Queen");

        });
        knightButton.setOnAction(event -> {
            primaryStage.close();
            handlePromotionSetType("Knight");
        });
        bishopButton.setOnAction(event -> {
            primaryStage.close();
            handlePromotionSetType("Bishop");
        });
        rookButton.setOnAction(event -> {
            primaryStage.close();
            handlePromotionSetType("Rook");
        });

        if (type == null) {
        }

    }

    // ...

    // Modify the handlePromotionSetType method to invoke the callback
    public void handlePromotionSetType(String type) {
        if (callback != null) {
            callback.onPromotionSelected(type);
        }
    }
    public String handlePromotionGetType() {
        return type;
        // Do something with the selected promotion type
    }

    public static void main(String[] args) {
        launch(args);
    }
}
