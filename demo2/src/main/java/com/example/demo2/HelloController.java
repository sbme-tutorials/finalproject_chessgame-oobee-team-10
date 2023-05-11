package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.*;
import java.util.Scanner;


public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button signupButton;
    @FXML
    private Label signupMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    HelloApplication m = new HelloApplication();

    public void signupUser() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        // Input validation
        if (checkSignup()) {


            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText("Please enter a username and password.");
                alert.showAndWait();
                return;
            }


            // Check if username is already taken
            if (isUsernameTaken(username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText("Username is already taken.");
                alert.showAndWait();
                return;
            }

            User user = new User(username, password);

            try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {
                writer.println(user.getUsername() + "," + user.getPassword());
                signupMessageLabel.setText("SignUp Completed!");
                m.changeScene("login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    private boolean checkSignup() {

        if ( usernameTextField.getText().toString().replaceAll("\\s+", "").matches("[a-zA-Z]+")&&(passwordPasswordField.getText().toString().replaceAll("\\s+", "").matches("\\d+"))) {
            signupMessageLabel.setText("Success !");
            return true;

        }
        else if (usernameTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText("Please enter a username and password.");
            alert.showAndWait();
            return false;
        }
        else {
            signupMessageLabel.setText("Wrong Username Or Password !");
            return false;
        }
    }


    public void CancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
