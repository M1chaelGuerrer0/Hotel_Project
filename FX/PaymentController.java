/*
    PAYMENT CONTROLLER
    4/16/25
    @author Ivan Amaya
    This class serves as the controller for all the buttons located on the Payment scene, also storing their card
    information to the database
*/

package com.example.Hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController {

    @FXML
    private AnchorPane paymentScene;

    @FXML
    private Button cancel;

    @FXML
    private Button continueButton;

    @FXML
    private CheckBox sameAs;

    @FXML
    private TextField cardNum;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardExp;

    @FXML
    private TextField cardCVC;

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
        Allows the user to reuse the address data they have stored under their account and populate that into the
        address information of their card info

        @param event listens for when an event fires
    */
    public void sameAs(ActionEvent event) throws IOException {
        System.out.println("1");

        // TODO : Fetch address info from database and fill in address text fields in the payment scene with it
    }

    /*
        Stores the users card information, and then switches them back to the prior scene

        @param event listens for when an event fires
    */
    public void continueButton(ActionEvent event) throws IOException {
        String cardNumber = cardNum.getText();
        System.out.println(cardNumber);
        String holderName = cardName.getText();
        System.out.println(holderName);
        String expiration = cardExp.getText();
        System.out.println(expiration);
        String cvc = cardCVC.getText();
        System.out.println(cvc);
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

        // TODO : Requires a scene swap back to the scene in which the user sets up their payment
    }


}
