package com.example.chessboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuControl implements Initializable {

    public Button newGameButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void newGameClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(gameApp.class.getResource("hello-view.fxml"));
        Scene gameScene = new Scene(fxmlLoader.load(), 1000, 750);

        // Get the current stage
        Stage stage = (Stage) newGameButton.getScene().getWindow();

        // Set the new scene on the stage
        stage.setScene(gameScene);
    }

    public void buttonEffect(MouseEvent mouseEvent) {
        newGameButton.setOnMouseEntered(event -> newGameButton.setStyle("-fx-background-color: #abe7f1;"));
        newGameButton.setOnMouseExited(event -> newGameButton.setStyle("-fx-background-color: #eeede6;"));
    }
}
