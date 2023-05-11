package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login  {
    public Login() throws IOException {
    }

    @FXML
    private Button login;
    @FXML
    private Button register;

    @FXML
    private Label wronglogin ;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    HelloApplication m = new HelloApplication();

    public void userLogin(ActionEvent event) throws IOException {

        String username1 = usernameField.getText();
        if (checkLogin()) {
            if (isUsernameTaken(username1)) {
                m.changeScene("afterLogin.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Create New Account");
                alert.showAndWait();
            }
        }
    }
    public void newAccount (ActionEvent event) throws IOException {
        m.changeScene("hello-view.fxml");
    }
    private boolean isUsernameTaken(String username) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }






    private boolean checkLogin() throws IOException{

        if ( usernameField.getText().toString().replaceAll("\\s+", "").matches("[a-zA-Z]+")&&(passwordField.getText().toString().replaceAll("\\s+", "").matches("\\d+"))) {
            wronglogin.setText("Success !");
            return true;
        }
        else if (usernameField.getText().isEmpty()&& passwordField.getText().isEmpty()){
            wronglogin.setText("Please Enter Your Data .");
            return false;

        }
        else {
            wronglogin.setText("Wrong Username Or Password !");
            return false;


        }
    }
}