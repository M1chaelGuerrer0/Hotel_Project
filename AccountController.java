package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
    ACCOUNT CONTROLLER
    4/16/25
    Ivan Amaya

    This class serves as the controller for all the buttons located on the Account scene, also allowing users to
    change the data they have saved to the database
*/
public class AccountController extends HotelDataBase {

    @FXML
    private AnchorPane accountScene;

    @FXML
    private Button editPersonal;

    @FXML
    private Button editAddress;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    private Button initialize;

    @FXML
    private Label emailDisplay;

    @FXML
    private Label passwordDisplay;

    @FXML
    private Label address1Display;

    @FXML
    private Label address2Display;

    @FXML
    private Label zipDisplay;

    @FXML
    private Label stateDisplay;

    @FXML
    private Label countryDisplay;

    @FXML
    private Label cityDisplay;

    private Parent root;

    private Scene scene;

    private Stage stage;


    // TODO : Need to retrieve and display account info onto "null" labels
    public void displayInfo() {
        emailDisplay.setText("null");
        passwordDisplay.setText("null");
        address1Display.setText("null");
        address2Display.setText("null");
        zipDisplay.setText("null");
        stateDisplay.setText("null");
        countryDisplay.setText("null");
        cityDisplay.setText("null");
    }

    /*
        Switches from the current scene back to the Parent scene

        @param event listens for when an event fires
    */
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
        Allows the user to switch their stored personal information in their account

        @param event listens for when an event fires
    */
    public void editButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
        Initializes the scene by firing the button automatically to display the user's account information
    */
    public void initialize() { initialize.fire(); }


}
