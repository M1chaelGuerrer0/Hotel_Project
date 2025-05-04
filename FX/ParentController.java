package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
    PARENT CONTROLLER
    4/16/25
    Ivan Amaya

    This class serves as the controller for all the buttons located on the Parent scene
*/
public class ParentController extends LoginController {

    @FXML
    private AnchorPane parentScene;

    @FXML
    private Button loginButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button checkButton;

    @FXML
    private Button workButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button initialize;

    private Parent root;

    private Scene scene;

    private Stage stage;

    public void displayLoginLogout() {
        if (logStatus) {
            logoutButton.setVisible(true);
            loginButton.setVisible(false);
            loginButton.setDisable(true);
        }

        if (!logStatus) {
            logoutButton.setVisible(false);
            loginButton.setVisible(true);
            loginButton.setDisable(false);
        
        if (empStatus) {
            checkButton.setVisible(true);
            checkButton.setDisable(false);
        }

        if (!empStatus) {
            checkButton.setVisible(false);
            checkButton.setDisable(true);
        }

        if (manStatus) {
            workButton.setVisible(true);
            workButton.setDisable(false);
        }

        if (!manStatus) {
            workButton.setVisible(false);
            workButton.setDisable(true);
        }

    }
    }

    /*
        Switches from the current scene to the Login scene

        @param event listens for when an event fires
    */
    public String loginButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        return "1";
    }

    /*
        Prompts the user with a warning message informing them that they are about log out, also swapping the
        logStatus to false indicating that they are no longer logged in

        @param event listens for when an event fires
    */
    public void logoutButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you would like to logout?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            logStatus = false;                                      // Indicates whether the user is logged in or not
            manStatus = false;
            empStatus = false;
            logoutButton.setVisible(false);
            loginButton.setVisible(true);
            loginButton.setDisable(false);
            checkButton.setVisible(false);
            checkButton.setDisable(true);
            workButton.setVisible(false);
            workButton.setDisable(true);

            stage = (Stage) parentScene.getScene().getWindow();
            System.out.println("Logged Out Successfully");
            System.out.println(logStatus);
        }
    }

    
    public void checkButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("check.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    public void workButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("work.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    /*
    
    Switches from the current scene to the Account scene
     @param event listens for when an event fires
     */
     public void accountButton(ActionEvent event) throws IOException {
         System.out.println(logStatus);
         if (LoginController.logStatus) {
             root = FXMLLoader.load(getClass().getResource("Account.fxml"));
             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         } else {
             root = FXMLLoader.load(getClass().getResource("login.fxml"));
             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         }
     }

    /*
        Initializes the scene by firing the button automatically to display the user's account information
    */
    public void initialize() {
        initialize.fire();

    }


}
