/*
    Check-in / Check-out controller
    4/18/25
    @author Mirella soto

    This class is the controller for the check-in/check-out scene,
    also serving as a current reservation and revenue list
*/

package com.example.Hotel;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class checkController {
    // the current time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private TableView<roomres> table;
    @FXML
    private TableColumn<roomres, String> name;
    @FXML
    private TableColumn<roomres, String> rooms;
    @FXML
    private TableColumn<roomres, String> checkInDate;
    @FXML
    private TableColumn<roomres, String> checkInTime;
    @FXML
    private TableColumn<roomres, String> checkOutDate;
    @FXML
    private TableColumn<roomres, String> checkOutTime;
    @FXML
    private TableColumn<roomres, String> revenue;

    // sets up the TableView when the controller is initialized
    @FXML
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<roomres, String>("name"));
        rooms.setCellValueFactory(new PropertyValueFactory<roomres, String>("rooms"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<roomres, String>("checkInDate"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<roomres, String>("checkOutDate"));
        checkInTime.setCellValueFactory(new PropertyValueFactory<roomres, String>("checkInTime"));
        checkOutTime.setCellValueFactory(new PropertyValueFactory<roomres, String>("checkOutTime"));
        revenue.setCellValueFactory(new PropertyValueFactory<roomres, String>("revenue"));
        // grabs list from room.java
        table.setItems(roomres.getInitialList());

    }

    //refresh button
    @FXML
    public void Refresh() {
        table.setItems(roomres.getInitialList());
    }

    /*
    check in button lists the current time under the selected reservation,
    pressed when customer checks in, it will also eventually update it on the database  */
    @FXML
    public void setCheckInTime() {
        roomres selectedRoom = table.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);
            selectedRoom.setCheckInTime(formattedDateTime);
            table.refresh();
        }
    }

    /*
    check out button lists the current time under the selected reservation,
    pressed when customer checks out, it will also eventually update it on the database  */
    @FXML
    public void setCheckOutTime(){
        roomres selectedRoom = table.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);
            selectedRoom.setCheckOutTime(formattedDateTime);
            table.refresh();
        }
    }
}