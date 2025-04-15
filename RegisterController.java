package com.example.gooncentral;

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

    public void mismatch(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText("Your passwords do not match.");
        alert.setContentText("Please review your passwords.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("1");
        }
    }

    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

        int match = password.compareTo(conPass);
        System.out.println(match);
        if (match == 0) {
            System.out.println("Your passwords match.");
            //store password
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Your passwords do not match.");
            mismatch(stage);
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
