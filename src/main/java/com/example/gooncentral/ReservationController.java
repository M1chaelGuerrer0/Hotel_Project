package com.example.gooncentral;
/*
This is the controller class is responsible for switching into
different scenes like reservation and registering.

@E.Sanchez
@version 1.0
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ReservationController extends HotelDataBase{

    User guest;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Card card_id;

    @FXML

    private DatePicker myDatePicker;

    @FXML

    private DatePicker myDatePicker2;

    @FXML

    private TextField checkInTime;
    @FXML

    private TextField checkOutTime;

@FXML
    private Button reserve1B;
    @FXML
    private Button reserve2B;
    @FXML
    private Button reserveS;
    @FXML
    private Button reserveAc;
    @FXML
    private Button reserveNc;
    @FXML
    private Button cancel;
    @FXML
    ImageView myImage;
    @FXML
    ImageView myImage2;
    @FXML
    ImageView myImage3;
    @FXML
    ImageView myImage4;
    @FXML
    ImageView myImage5;
    public static int lastInsertedRoomId;
    public static int lastInsertedRoomId2;
    public Room selectedRoom;


    public void setSelectedRoom(Room room) {
        this.selectedRoom = room;

    }
    public void setCard(Card card_id){
        this.card_id= card_id;

    }

    /*
        Switches from the current scene back to the Login scene

        @param event listens for when an event fires
    */
    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene0(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Parent.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void switchToRoomList(ActionEvent event) {
        try {

            Room newRoom = new Room(0, "1 BedRoom", 50.00, 2, "OPEN");
            HotelDataBase.addRoom(newRoom);
           // lastInsertedRoomId = newRoom.getRoom_Number();
            Room newRoom2 = new Room(1, "1 BedRoom", 50.00, 2, "OPEN");
            addRoom(newRoom2);
           // lastInsertedRoomId2 = newRoom2.getRoom_Number();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("roomList.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
                e.printStackTrace();
            }
        }

    public void switchToRoomList2(ActionEvent event) {
        try {
            Room newRoom3 = new Room(0, "2 BedRoom", 200.00, 4, "OPEN");
            HotelDataBase.addRoom(newRoom3);

            Room newRoom4 = new Room(0, "2 BedRoom", 200.00, 4, "OPEN");
            addRoom(newRoom4);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("twoBedList.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void switchToRoomList3(ActionEvent event) {
        try {
            Room newRoom3 = new Room(0, "suite", 500.00, 7, "OPEN");
            HotelDataBase.addRoom(newRoom3);

            Room newRoom4 = new Room(0, "2 BedRoom", 500.00, 7, "OPEN");
            addRoom(newRoom4);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("suiteList.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void switchToRoomList4(ActionEvent event) {
        try {
            Room newRoom3 = new Room(0, "AC", 180.00, 4, "OPEN");
            HotelDataBase.addRoom(newRoom3);

            Room newRoom4 = new Room(0, "AC", 180.00, 4, "OPEN");
            addRoom(newRoom4);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("acList.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void switchToRoomList5(ActionEvent event) {
        try {
            Room newRoom3 = new Room(0, "NO AC", 160.00, 4, "OPEN");
            HotelDataBase.addRoom(newRoom3);

            Room newRoom4 = new Room(0, "NO AC", 160.00, 4, "OPEN");
            addRoom(newRoom4);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("noAcList.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    This method returns the user back to the page where they choose their room
     */
    public void switchToScene1(ActionEvent event){
        try {
            guest = HotelDataBase.getGuest(LoginController.inputEmail);
            LocalDate myDate = myDatePicker.getValue();
            LocalDate myDate2 = myDatePicker2.getValue();
            myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            myDate2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Date checkIn = Date.valueOf(myDate);
            Date checkOut = Date.valueOf(myDate2);

            Reservation res = new Reservation();
            res.setRoom_Number(selectedRoom.getRoom_Number());
            res.setGuest_id(guest.getUser_id());
            res.setCard_id(card_id.getCard_id());
            res.setName(guest.getFirst_Name());
            res.setCheck_In_Date(checkIn);
            res.setCheck_Out_Date(checkOut);
            res.setCheck_In_Time(Time.valueOf("00:00:00"));
            res.setCheck_Out_Time(Time.valueOf("00:00:00"));
            res.setGuest_id(guest.getUser_id());
            res.setName(guest.getFirst_Name());

            HotelDataBase.addReservation(res);
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
    /*
    This takes the user to picking a check in/out date by using the reserve button
     */
  /*
    In case the user is not logged in it will take them to a register page
     */
    public void switchToScene3(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("guestAddOn.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
