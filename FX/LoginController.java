package com.example.Hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    LOGIN CONTROLLER
    4/16/25
    @author Ivan Amaya
    @version 1.0
    
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
    private TextField user;

    @FXML
    private TextField password;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public static Boolean logStatus = false;

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
        // TODO : Verify that the user's entered data matches with info found in the database

        logStatus = true;

        root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println(logStatus);
    }


}