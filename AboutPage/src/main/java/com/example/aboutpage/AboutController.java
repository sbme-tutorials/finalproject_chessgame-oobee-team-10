package com.example.aboutpage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AboutController {

   @FXML //
    ImageView myImageView=new ImageView();
    public void displayImage(){
        Image myImage =new Image(Objects.requireNonNull(getClass().getResourceAsStream("download.jpeg")));
        Image myImage2 =new Image(Objects.requireNonNull(getClass().getResourceAsStream("chess-board-game-concept-business-ideas-competition (1).jpg")));

        myImageView.setImage(myImage);
        myImageView.setImage(myImage2);
    }

   /* @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private ImageView imageView;
    public void initialize() {
        String imagePath = "\"C:\\Users\\als7aba\\IdeaProjects\\AboutPage\\src\\img\\chess-board-game-concept-business-ideas-competition (1).jpg\"";
        Image image = new Image(imagePath);
        imageView.setImage(image);
    }*/
}