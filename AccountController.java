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

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPassword;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField street1;

    @FXML
    private TextField street2;

    @FXML
    private TextField userCity;

    @FXML
    private TextField zip;

    @FXML
    private TextField userState;

    @FXML
    private TextField userCountry;


    private Parent root;

    private Scene scene;

    private Stage stage;


    /*
        Warming prompt indicating that the users passwords do not match
    */
    public void mismatch() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText("Your passwords do not match.");
        alert.setContentText("Please review your passwords.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("1");
        }
    }

    /*
        Allows new inputs to replace old email and password, then store that in the database

        @param event listens for when an event fires
    */
    public void saveButton(ActionEvent event) throws IOException {
        String firstname = firstName.getText();
        System.out.println(firstname);
        String lastname = lastName.getText();
        System.out.println(lastname);
        String email = userEmail.getText();
        System.out.println(email);
        String password = userPassword.getText();
        System.out.println(password);
        String conPass = confirmPassword.getText();
        System.out.println(conPass);
        String phone = phoneNum.getText();
        System.out.println(phone);
        String address1 = street1.getText();
        System.out.println(address1);
        String address2 = street2.getText();
        System.out.println(address2);
        String city = userCity.getText();
        System.out.println(city);
        String country = userCountry.getText();
        System.out.println(country);
        String state = userState.getText();
        System.out.println(state);
        String zipCode = zip.getText();
        System.out.println(zipCode);

        User guest = new User();

        int match = password.compareTo(conPass);           // compares the two password input to confirm if they match
                                                           // will return 0, if a mismatch will return an int != 0
        System.out.println(match);


        /*
            Checks whether match was 0, and if so then the password is saved and the scene is swapped to back to
            the Login scene, and if not mismatch() is called and the user is prompted to check their entered
            information
        */
        if (match == 0) {
            // Store all the above info into the database
            guest.setFirst(firstname);
            guest.setLast(lastname);
            guest.setEmail(email);
            guest.setPassword(password);
            guest.setPhoneNumber(phone);
            guest.setAddress1(address1);
            guest.setAddress2(address2);
            guest.setCity(city);
            guest.setCountry(country);
            guest.setState(state);
            guest.setZipCode(zipCode);
            guest.displayUser();                                // display current guest

            addGuest(guest);
            System.out.println("Your passwords match.");

            root = FXMLLoader.load(getClass().getResource("Account.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Your passwords do not match.");
            mismatch();
        }
    }

    /*
        Switches from the current scene back to the Parent scene

        @param event listens for when an event fires
    */
    public void cancelAccount(ActionEvent event) throws IOException {
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
        Switches from the editing Account info scene back to the regular Account scene

        @param event listens for when an event fires
    */
    public void cancelEdit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
