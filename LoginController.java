package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    LOGIN CONTROLLER
    4/16/25
    Ivan Amaya

    This class serves as the controller for all the buttons located on the Login scene, while verifying that the
    account does indeed exist within the database
*/
public class LoginController {

    @FXML
    private AnchorPane loginScene;

    @FXML
    private Button continueAsGuest;

    @FXML
    private Button forgotPassword;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPassword;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public static Boolean logStatus = false;

    public String password;


    /*
        Warming prompt indicating that the users password was incorrect
    */
    public void mismatch() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Mismatch");
        alert.setHeaderText("There was an issue with you email and/or password");
        alert.setContentText("Please review your information.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("1");
        }
    }

    /*
        Switches from the current scene back to the Parent scene

        @param event listens for when an event fires
    */
    public void continueAsGuestButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
        Switches from the current scene to the Register scene

        @param event listens for when an event fires
    */
    public void registerButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerLog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
        Verifies that the entered information exists in the database, and then logs the user in while swapping
        back to the Parent scene. Also changes the value of logStatus to true.

        @param event listens for when an event fires
    */
    public void loginButton(ActionEvent event) throws IOException {
        String currentEmail = userEmail.getText();
        String compPassword = userPassword.getText();

        // Verify that the user's entered data matches with info found in the database
        User guest = new User();

        try {
            guest = HotelDataBase.getGuest(currentEmail);
            password = guest.getPassword();
            int match = compPassword.compareTo(password);           // compares the two password input to confirm if they match
            System.out.println(match);                              // will return 0, if a mismatch will return an int != 0

            /*
                Checks whether match was 0, and if so then the user may proceed to the Parent scene, and if not mismatch()
                is called and the user is prompted to check their entered information
            */
            if (match == 0) {
                logStatus = true;
                System.out.println("logStatus: " + logStatus);
                System.out.println("Your password is correct.");
                root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Your password did not match.");
                mismatch();
            }
        } catch (RuntimeException e) {
            mismatch();
        }


    }


}