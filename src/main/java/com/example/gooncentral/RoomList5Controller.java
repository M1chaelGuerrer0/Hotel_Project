package com.example.gooncentral;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
public class RoomList5Controller {
    @FXML
    private TableView<Room> roomTableView;

    @FXML
    private TableColumn<Room, Integer> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> roomTypeColumn;
    @FXML
    private TableColumn<Room, Integer> roomCapacityColumn;
    @FXML
    private TableColumn<Room, Double> pricePerDayColumn;
    @FXML
    private TableColumn<Room, String> roomStatusColumn;
    @FXML
    private Button cancel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("room_Number"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("room_Type"));
        roomCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("room_Capacity"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("price_Per_Night"));
        roomStatusColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        List<Room> rooms = HotelDataBase.getRoomOfType("NO AC");
        if (rooms != null && !rooms.isEmpty()) {
            roomTableView.setItems(FXCollections.observableArrayList(rooms));
        } else {
            System.out.println("No room data retrieved from the database.");
        }
        Room chosen =   roomTableView.getSelectionModel().getSelectedItem();
        roomTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !roomTableView.getSelectionModel().isEmpty()) {
                Room selectedRoom = roomTableView.getSelectionModel().getSelectedItem();
                selectedRoom.setAvailability("CLOSED");
                HotelDataBase.updateRoom(selectedRoom);


                try {
                    loadReservationScene(selectedRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reservationError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Reservation Error");
        alert.setHeaderText("This has already been reserved.");
        alert.setContentText("Please make another selection.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("1");
        }
    }

    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadReservationScene(Room room) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
            Parent root = loader.load();
            PaymentController controller = loader.getController();
            controller.setSelectedRoom(room);
            Stage stage = (Stage) roomTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception IllegalStateException) {
            reservationError();
        }
    }
}
