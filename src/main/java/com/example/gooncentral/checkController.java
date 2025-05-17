package com.example.gooncentral;

/*
     Check-in / Check-out controller
     5/3/25
     @author Mirella soto

     This class is the controller for the check-in/check-out scene,
     displays a current reservation table and room table
 */

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class checkController extends HotelDataBase {
    //Time formatting
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> name;
    @FXML
    private TableColumn<Reservation, Integer> rooms;
    @FXML
    private TableColumn<Reservation, String> checkInDate;
    @FXML
    private TableColumn<Reservation, String> checkInTime;
    @FXML
    private TableColumn<Reservation, String> checkOutDate;
    @FXML
    private TableColumn<Reservation, String> checkOutTime;

    @FXML
    private TableView<Room> table0;
    @FXML
    private TableColumn<Room, String> type;
    @FXML
    private TableColumn<Room, Integer> room;
    @FXML
    private TableColumn<Room, String> price;
    @FXML
    private TableColumn<Room, String> capacity;
    @FXML
    private TableColumn<Room, String> availability;
    @FXML
    private Button cancel;

    private Parent root;

    private Scene scene;

    private Stage stage;

    // sets up the TableView when the controller is initialized
    @FXML
    public void initialize() {

        name.setCellValueFactory(new PropertyValueFactory<Reservation, String>("name"));
        rooms.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room_Number"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_In_Date"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_Out_Date"));
        checkInTime.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_In_Time"));
        checkOutTime.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_Out_Time"));

        // gets list from getInitialList() method
        table.setItems(FXCollections.observableArrayList(getReservations()));


        type.setCellValueFactory(new PropertyValueFactory<Room, String>("room_Type"));
        room.setCellValueFactory(new PropertyValueFactory<Room, Integer>("room_Number"));
        price.setCellValueFactory(new PropertyValueFactory<Room, String>("price_Per_Night"));
        capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("room_Capacity"));
        availability.setCellValueFactory(new PropertyValueFactory<Room, String>("availability"));

        table0.setItems(FXCollections.observableArrayList(getRooms()));

    }

    /*
        Refresh button refreshes the table for any changes made
        pressed when the worker wants to clear reservations that have concluded
    */
    @FXML
    public void Refresh() {
        table.setItems(FXCollections.observableArrayList(getReservations()));
    }

    /*
        Delete button deletes selected reservation
        pressed when the worker wants to delete a reservation
    */

    @FXML
    public void Delete() {
        Reservation selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int id = selected.getReserve_id();
            HotelDataBase.deleteReservation(id);
            table.refresh();
        }
    }

    /*
        Check-in button lists the current time under the selected reservation,
        pressed when the customer checks in and updates info in database
    */
    @FXML
    public void setCheckInTime() {
        Reservation selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String time = LocalDateTime.now().format(formatter);
            selected.setCheck_In_Time(Time.valueOf(time));
            HotelDataBase.updateReservation(selected);
            table.refresh();
        }
    }
    /*
        check out button lists the current time under the selected reservation,
        pressed when customer checks out and updates info in database
    */
    @FXML
    public void setCheckOutTime() {
        Reservation selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String time = LocalDateTime.now().format(formatter);
            selected.setCheck_Out_Time(Time.valueOf(time));
            HotelDataBase.updateReservation(selected);
            table.refresh();
        }
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
}
