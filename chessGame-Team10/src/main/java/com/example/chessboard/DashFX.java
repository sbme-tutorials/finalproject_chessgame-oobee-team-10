package com.example.chessboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashFX implements Initializable {
    //sideBar buttons
    @FXML
    Button mainButt, startButt;
    @FXML
    AnchorPane innerScene, MainScene;
    @FXML
    BorderPane Main;
    @FXML
    private ComboBox<String> boardDesign;

    public String boardtheme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardDesign.getItems().addAll("Demo", "Coral", "Dusk","Wheat","Marine","Emerald","Sandcastle");
        boardDesign.setOnAction(e -> {
            boardtheme = boardDesign.getSelectionModel().getSelectedItem();});



        // Change color and text color when button is clicked
        startButt.setOnAction(e -> {
            startButt.setStyle("-fx-background-color: #0000FF; -fx-text-fill: #FFFFFF;");

            GridPane board= new GridPane();
            Game newgame = new Game(board,boardtheme);
            FXMLLoader gameScene = new FXMLLoader(gameApp.class.getResource("hello-view.fxml"));
            Main.setCenter(board);


        });

        // Change background color when mouse enters
        startButt.setOnMouseEntered(e -> {
            startButt.setStyle("-fx-background-color: #FF0000;");
        });

        // Change background color when mouse exits
        startButt.setOnMouseExited(e -> {
            startButt.setStyle("-fx-background-color: #FFFFFF;");
        });

    }
    public void mainClicked(ActionEvent actionEvent) throws IOException {

    }


    public void startClicked(MouseEvent mouseEvent) {
    }

    public void startIn(MouseEvent mouseEvent) {
    }

    public void startOut(MouseEvent mouseEvent) {
    }
}
