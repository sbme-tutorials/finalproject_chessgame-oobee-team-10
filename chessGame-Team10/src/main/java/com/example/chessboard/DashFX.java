package com.example.chessboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    @FXML
    AnchorPane center;
    @FXML
    AnchorPane gameContainer;
    public Button newGamePageButt;
    public Button Home;
    public Button scoresPageButt;
    public Button aboutPageButt;
    //sideBar buttons

    @FXML
    Button mainButt, startButt;
    @FXML
    AnchorPane innerScene, MainScene;
    @FXML
    BorderPane Main;
    @FXML
    private ComboBox<String> boardDesign;
    @FXML
    private ComboBox<String> pieceDesign;

    public String boardtheme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        center =new AnchorPane();
        GameDataModel gamedata =new GameDataModel();
        boardDesign.getItems().addAll("Demo", "Coral", "Dusk","Wheat","Marine","Emerald","Sandcastle");
        boardDesign.setOnAction(e -> {
            boardtheme = boardDesign.getSelectionModel().getSelectedItem();});
        pieceDesign.getItems().addAll("alpha", "california");
        pieceDesign.setOnAction(e -> {
            Piece.setPieceDesign(pieceDesign.getSelectionModel().getSelectedItem());});



        // Change color and text color when button is clicked

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
        startButt.setStyle("-fx-background-color: #0000FF; -fx-text-fill: #FFFFFF;");

        GridPane board = new GridPane();
        Game newgame = new Game(board, boardtheme,new GameDataModel());
        FXMLLoader gameScene = new FXMLLoader(gameApp.class.getResource("sample.fxml"));
        try {
            gameContainer.getChildren().add(gameScene.load());
            gameContainer.toFront();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startIn(MouseEvent mouseEvent) {

    }

    public void startOut(MouseEvent mouseEvent) {
    }
    @FXML
    private AnchorPane homePage;
    public void displayHome (ActionEvent event) throws IOException{
        homePage.toFront();

    }
    @FXML
    private AnchorPane newGamePage;
    public void displayGame (ActionEvent event) throws IOException{
        newGamePage.toFront();

    }
    @FXML
    private AnchorPane aboutPage;
    public void displayAbout (ActionEvent event) throws IOException{
        aboutPage.toFront();

    }

    private void handle(ActionEvent e) throws IOException {



    }
}
