package com.example.Hotel;

/*
    Check-in / Check-out controller
    4/18/25
    @author Mirella soto

    This class is the controller for the check-in/check-out scene,
    also serving as a current reservation and revenue list
*/

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    private TableColumn<Reservation, String> revenue;

    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // sets up the TableView when the controller is initialized
    @FXML
    public void initialize() {

        name.setCellValueFactory(new PropertyValueFactory<Reservation, String>("name"));
        rooms.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room_Number"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_In_Date"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_Out_Date"));
        checkInTime.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_In_Time"));
        checkOutTime.setCellValueFactory(new PropertyValueFactory<Reservation, String>("check_Out_Time"));
        revenue.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(""));

        revenue.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Reservation reservation = (Reservation) getTableRow().getItem();
                    int pricePerNight = getRoomPrice(reservation.getRoom_Number());
                    int calculatedRevenue = calculateRevenue(reservation, pricePerNight);
                    setText(String.format("$%.2f", calculatedRevenue));
                }
            }
        });

        // gets list from getInitialList() method
        table.setItems(HotelDataBase.getCheckList());
    }

    // get the price per night for a room from the database
    private int getRoomPrice(int roomNumber) {
        int price = 0;
        String query = "SELECT price_per_night FROM room WHERE room_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    price = rs.getInteger("price_per_night");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    // Method to calculate the revenue based on price per night and stay duration
    private int calculateRevenue(Reservation reservation, int pricePerNight) {
        try {
            // Parse check-in and check-out dates (only the date part)
            LocalDate checkInDate = LocalDate.parse(reservation.getCheck_In_Date().toString());
            LocalDate checkOutDate = LocalDate.parse(reservation.getCheck_Out_Date().toString());

            // Calculate the number of days stayed (using the date only)
            long stayLength = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            return stayLength * pricePerNight;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
        Refresh button refreshes the table for any changes made
        pressed when the worker wants to clear reservations that have concluded
    */
    @FXML
    public void Refresh() {
        table.setItems(HotelDataBase.getCheckList());
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
}