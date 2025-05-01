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
public class AccountController extends LoginController {

    @FXML
    private AnchorPane accountScene;

    @FXML
    private Button edit;

    @FXML
    private Button cancel;

    @FXML
    private Button initialize;

    @FXML
    private Label firstDisplay;

    @FXML
    private Label lastDisplay;

    @FXML
    private Label emailDisplay;

    @FXML
    private Label passwordDisplay;

    @FXML
    private Label phoneDisplay;

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


    /*
        Fires on the scene's startup, displaying the user's account information on the Account scene
    */
    public void displayInfo() {
        User guest;
        guest = HotelDataBase.getGuest(inputEmail);

        assert guest != null;
        String firstName = guest.getFirst_Name();
        String lastName = guest.getLast_Name();
        String email = guest.getEmail();
        String password = guest.getPassword();
        String phoneNum = guest.getPhone_Number();
        String address1 = guest.getAddress1();
        String address2 = guest.getAddress2();
        String zip = guest.getZip_Code();
        String state = guest.getState();
        String country = guest.getCountry();
        String city = guest.getCity();

        firstDisplay.setText(firstName);
        lastDisplay.setText(lastName);
        emailDisplay.setText(email);
        passwordDisplay.setText(password);
        phoneDisplay.setText(phoneNum);
        address1Display.setText(address1);
        address2Display.setText(address2);
        zipDisplay.setText(zip);
        stateDisplay.setText(state);
        countryDisplay.setText(country);
        cityDisplay.setText(city);
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
    public void initialize() {
        initialize.fire();
    }


}
