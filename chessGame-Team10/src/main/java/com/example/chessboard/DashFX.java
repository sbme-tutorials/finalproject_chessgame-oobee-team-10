package com.example.chessboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashFX implements Initializable {
    public Button startButt1;
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
    public Button existButt;
    @FXML
    Button mainButt, startButton;
    @FXML
    ImageView backGround,homeIcon,newGameIcon,aboutIcon,onePlayerIcon;
    @FXML
    AnchorPane mainPage;
    @FXML
    AnchorPane innerScene, MainScene;
    @FXML
    BorderPane Main;
    @FXML
    private ComboBox<String> boardDesign;
    @FXML
    private ComboBox<String> pieceDesign;
    @FXML
    private AnchorPane homePage;
    public static String boardtheme;
    @FXML
    private AnchorPane newGamePage;

    @FXML
    private AnchorPane aboutPage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backGround.setImage(new Image("File:images/backGround.png"));
        homeIcon.setImage(new Image("File:images/icons/home.png"));
        aboutIcon.setImage(new Image("File:images/icons/info.png"));
        newGameIcon.setImage(new Image("File:images/icons/console.png"));
        onePlayerIcon.setImage(new Image("File:images/icons/alone.png"));

        GameDataModel gamedata =new GameDataModel();
        boardDesign.getItems().addAll("Demo", "Coral", "Dusk","Wheat","Marine","Emerald","Sandcastle","Sayed" , "Ahmed");
        boardDesign.setOnAction(e -> {
            boardtheme = boardDesign.getSelectionModel().getSelectedItem();});
        pieceDesign.getItems().addAll("alpha", "california","chessnut" , "chessicons" , "governor");
        pieceDesign.setOnAction(e -> {
            Piece.setPieceDesign(pieceDesign.getSelectionModel().getSelectedItem());});

        gamedata.setTheme(boardtheme);
        gamedata.setPieceTheme(String.valueOf(pieceDesign));



        // Change color and text color when button is clicked

        // Change background color when mouse enters


    }










    
    public void displayHome (ActionEvent event) throws IOException{
        homePage.toFront();

    }

    public void displayGame (ActionEvent event) throws IOException{
        newGamePage.toFront();

    }

    public void displayAbout (ActionEvent event) throws IOException{
        aboutPage.toFront();

    }
    public void goOut (ActionEvent e) {
        Stage stage = (Stage) existButt.getScene().getWindow();
        stage.close();
    }

    public void startClicked(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        center.getChildren().add(root);
    }
}