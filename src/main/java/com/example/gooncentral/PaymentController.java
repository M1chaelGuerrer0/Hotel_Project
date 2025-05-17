/*
    PAYMENT CONTROLLER
    4/16/25
    @author Ivan Amaya
    This class serves as the controller for all the buttons located on the Payment scene, also storing their card
    information to the database
*/
package com.example.gooncentral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class PaymentController  extends LoginController{
    private ObservableList<String> savedCards = FXCollections.observableArrayList();

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
    @FXML
    private ComboBox cards;

    private Parent root;

    private Scene scene;



    public Room selectedRoom;
    public void setSelectedRoom(Room room) {
        this.selectedRoom = room;

    }
    private Stage stage;

    @FXML
    public void fill() {

        cards.setItems(savedCards);


    }

    /*
        Switches from the current scene back to the Login scene

        @param event listens for when an event fires
    */
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
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
        if (sameAs.isSelected()) {
            User guest = HotelDataBase.getGuest(LoginController.inputEmail);

            street1.setText(guest.getAddress1());
            street2.setText(guest.getAddress2());
            userCity.setText(guest.getCity());
            userCountry.setText(guest.getCountry());
            userState.setText(guest.getState());
            zip.setText(guest.getZip_Code());

        }
    }
    /*
    After payment returns user to reservation page
     */

    public void switchToScene1(ActionEvent event){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void autofillCardFields(Card card) {
        cardNum.setText(card.getCard_Number());
        cardName.setText(card.getHolder_Name());
        cardExp.setText(card.getExpiration());
        cardCVC.setText(card.getCvc());

        street1.setText(card.getAddress1());
        street2.setText(card.getAddress2());
        userCity.setText(card.getCity());
        userCountry.setText(card.getCountry());
        userState.setText(card.getState());
        zip.setText(card.getZip_Code());
    }
    /*
        Stores the users card information, and then switches them back to the prior scene

        @param event listens for when an event fires
    */
    public void continueButton(ActionEvent event) throws IOException {

        User guest = HotelDataBase.getGuest(LoginController.inputEmail);
        if(LoginController.inputEmail != null) {
            Card card = new Card();
            card.setHolder_Name(cardName.getText());
            card.setGuest_id(guest.getUser_id());
            card.setCard_Number(cardNum.getText());
            card.setExpiration(cardExp.getText());
            card.setCvc(cardCVC.getText());
            card.setAddress1(street1.getText());
            card.setCity(userCity.getText());
            card.setCountry(userCountry.getText());
            card.setState(userState.getText());
            card.setZip_Code(zip.getText());
            String cardList = cardName.getText() + " -  " + cardNum.getText();
            savedCards.add(cardList);
            fill();
            HotelDataBase.addCard(card);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene2.fxml"));
            Parent root = fxmlLoader.load();
            ReservationController reservationController = fxmlLoader.getController();
            reservationController.setSelectedRoom(selectedRoom);
            reservationController.setCard(card);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registerLog.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            }
    }
    public void add(){
        String cardList = cardName.getText() + " -  " + cardNum.getText();
        savedCards.add(cardList);
        fill();

    }

}
