package com.example.chessboard;//package com.example.chessboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckingWindow extends Application {
    public void start(Stage primaryStage) {
        // Create a grid pane to hold the buttons and label
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create a label with the Warning message
        Label promotionLabel1 = new Label("You must be careful, Your King is Under-attack!!");

        // Create button for the Warning message
        Button button = new Button("Okay");

        // Add the label and button to the grid pane
        gridPane.add(promotionLabel1, 0, 0);
        gridPane.add(button, 0, 1);

        // Center the button in the grid pane
        GridPane.setHalignment(button, javafx.geometry.HPos.CENTER);

        Scene scene = new Scene(gridPane, 400, 100);

        // Set the stage title and scene
        primaryStage.setTitle("Checking");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(event -> {
            primaryStage.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
