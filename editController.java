package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class editController extends AccountController {

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    private Button initialize;

    @FXML
    private TextField userFirst;

    @FXML
    private TextField userLast;

    @FXML
    private TextField userPhone;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPassword;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField userStreet1;

    @FXML
    private TextField userStreet2;

    @FXML
    private TextField userCity;

    @FXML
    private TextField userZip;

    @FXML
    private TextField userState;

    @FXML
    private TextField userCountry;

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


    public void setup() {
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

        userFirst.setText(firstName);
        userLast.setText(lastName);
        userEmail.setText(email);
        userPassword.setText(password);
        userPhone.setText(phoneNum);
        userStreet1.setText(address1);
        userStreet2.setText(address2);
        userZip.setText(zip);
        userState.setText(state);
        userCountry.setText(country);
        userCity.setText(city);

    }

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
        Switches from the current scene back to the Account scene

        @param event listens for when an event fires
    */
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /*
        Allows new inputs to replace old email and password, then store that in the database

        @param event listens for when an event fires
    */
    public void saveButton(ActionEvent event) throws IOException {
        String firstname = userFirst.getText();
        String lastname = userLast.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        String conPass = confirmPassword.getText();
        String phone = userPhone.getText();
        String address1 = userStreet1.getText();
        String address2 = userStreet2.getText();
        String city = userCity.getText();
        String country = userCountry.getText();
        String state = userState.getText();
        String zipCode = userZip.getText();

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(conPass);
        System.out.println(phone);
        System.out.println(address1);
        System.out.println(address2);
        System.out.println(city);
        System.out.println(country);
        System.out.println(state);
        System.out.println(zipCode);

        int match = password.compareTo(conPass);           // compares the two password input to confirm if they match
                                                           // will return 0, if a mismatch will return an int != 0
        System.out.println(match);

        /*
            Checks whether match was 0, and if so then the information is saved and the scene is swapped to back to
            the Login scene, and if not mismatch() is called and the user is prompted to check their entered
            information
        */
        if (match == 0) {
            System.out.println("Your passwords match.");

            User guest;
            guest = HotelDataBase.getGuest(inputEmail);
            assert guest != null;

            // Updates changed info of the logged-in user and then store that in the database

            // TODO : Needs to update existing user, not create a new one (issue is within the email being targeted)
            guest.setFirst_Name(firstname);
            guest.setLast_Name(lastname);
            guest.setPassword(password);
            guest.setPhone_Number(phone);
            guest.setAddress1(address1);
            guest.setAddress2(address2);
            guest.setCity(city);
            guest.setCountry(country);
            guest.setState(state);
            guest.setZip_Code(zipCode);

            HotelDataBase.updateGuest(guest);

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
        Initializes the scene by firing the button automatically to set the user email being targeted
    */
    public void initialize() {
        initialize.fire();
    }
}
