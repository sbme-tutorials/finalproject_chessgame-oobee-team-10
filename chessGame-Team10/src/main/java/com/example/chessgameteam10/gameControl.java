package com.example.chessgameteam10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class gameControl {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}