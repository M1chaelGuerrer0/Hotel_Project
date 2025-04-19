package com.example.Hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;

import java.io.IOException;
import java.util.ResourceBundle;

/*
    REGISTER CONTROLLER
    4/16/25
    @author Ivan Amaya
    @version 1.0

    This class serves as the controller for all the buttons located on the Register scene, along with the saving of
    the user's information into the database
*/
public class RegisterController {

    @FXML
    private AnchorPane registerScene;

    @FXML
    private Button cancel;

    @FXML
    private Button register;

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

    /*@FXML
    private ChoiceBox<String> stateBox;

    private String[] states = {
            "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC",
            "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
            "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
            "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK",
            "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "VI", "WA", "WV", "WI", "WY"
    };*/

    /*@FXML
    private ChoiceBox<String> countryBox;

    private String[] countries = {
            "United States"
    };*/

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
        Switches from the current scene back to the Login scene

        @param event listens for when an event fires
    */
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
        Stores all the new user's information into the database, then switches from the current scene back to the
        Login scene. Entered password is also compared with the confirm password to verify that the password is
        correct, and then saved.

        @param event listens for when an event fires
    */
    public void registerButton(ActionEvent event) throws IOException {
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

        // TODO : store all the above info into the database

        int match = password.compareTo(conPass);           // compares the two password input to confirm if they match
                                                           // will return 0, if a mismatch will return an int != 0
        System.out.println(match);

        /*
            Checks whether match was 0, and if so then the password is saved and the scene is swapped to back to
            the Login scene, and if not mismatch() is called and the user is prompted to check their entered
            information
        */
        if (match == 0) {
            System.out.println("Your passwords match.");
            // TODO : store the new password into the database
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Your passwords do not match.");
            mismatch();
        }
    }

    /*public void initialize(URL arg0, ResourceBundle arg1) {
        stateBox.getItems().addAll(states);

        stateBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "State" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });

        countryBox.getItems().addAll(countries);

        countryBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s == null) ? "Country" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });
    }*/


}
