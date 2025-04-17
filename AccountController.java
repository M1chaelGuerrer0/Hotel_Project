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
import javafx.scene.control.Label;
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
public class AccountController {

    @FXML
    private AnchorPane accountScene;

    @FXML
    private Button editPersonal;

    @FXML
    private Button editAddress;

    @FXML
    private Button cancel;

    @FXML
    private Label emailDisplay;

    @FXML
    private Label passwordDisplay;

    @FXML
    private Label address1Display;

    @FXML
    private Label address2Display;

    @FXML
    private Label stateDisplay;

    @FXML
    private Label countryDisplay;

    @FXML
    private Label cityDisplay;

    private Parent root;

    private Scene scene;

    private Stage stage;

    /*
        Allows the user to switch their stored personal information of email and password from their account

        @param newEmail the new email the user wishes to replace the old one stored
        @param newPass the new password the user wishes to replace the old one stored
    */
    public void editPersonalButton(String newEmail, String newPass) {
        System.out.println("1");

        // TODO : Allow new inputs to replace old email and password, then store that in the database
    }

    /*
        Allows the user to switch their stored address information

        @param newAddress1 the new address1 the user wishes to replace the old one stored
        @param newAddress2 the new address2 the user wishes to replace the old one stored
        @param newState the new state the user wishes to replace the old one stored
        @param newCountry the new country the user wishes to replace the old one stored
        @param newCity the new city the user wishes to replace the old one stored
        @param newZip the new zip code the user wishes to replace the old one stored
    */
    public void editAddressButton
    (String newAddress1, String newAddress2, String newState, String newCountry, String newCity, String newZip) {
        System.out.println("1");

        // TODO : Allow new inputs to replace old Address info, then store that in the database
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

}
