package com.example.gooncentral;

/*
     Check-in / Check-out controller
     5/3/25
     @author Mirella soto

     This class is the controller for the staff scene,
     displays a current staff table and guest table
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

public class workController extends HotelDataBase {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, Integer> Id;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> address;
    @FXML
    private TableColumn<User, String> zip;
    @FXML
    private TableView<User> table0;
    @FXML
    private TableColumn<User, String> firstName0;
    @FXML
    private TableColumn<User, String> lastName0;
    @FXML
    private TableColumn<User, Integer> Id0;
    @FXML
    private TableColumn<User, String> email0;
    @FXML
    private TableColumn<User, String> phone0;
    @FXML
    private TableColumn<User, String> address0;
    @FXML
    private TableColumn<User, String> zip0;
    @FXML
    private Button cancel;

    private Parent root;

    private Scene scene;

    private Stage stage;

    // sets up the TableView when the controller is initialized
    @FXML
    public void initialize() {

        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("first_Name"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("last_Name"));
        Id.setCellValueFactory(new PropertyValueFactory<User, Integer>("user_id"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone_Number"));
        address.setCellValueFactory(new PropertyValueFactory<User, String>("address1"));
        zip.setCellValueFactory(new PropertyValueFactory<User, String>("zip_Code"));


        // gets list from getInitialList() method
        table.setItems(FXCollections.observableArrayList(getWorkers()));

        firstName0.setCellValueFactory(new PropertyValueFactory<User, String>("first_Name"));
        lastName0.setCellValueFactory(new PropertyValueFactory<User, String>("last_Name"));
        Id0.setCellValueFactory(new PropertyValueFactory<User, Integer>("user_id"));
        email0.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        phone0.setCellValueFactory(new PropertyValueFactory<User, String>("phone_Number"));
        address0.setCellValueFactory(new PropertyValueFactory<User, String>("address1"));
        zip0.setCellValueFactory(new PropertyValueFactory<User, String>("zip_Code"));


        // gets list from getInitialList() method
        table0.setItems(FXCollections.observableArrayList(getGuests()));
    }

    /*
        Refresh button refreshes the table for any changes made
        pressed when the manager wants to clear workers that have been fired/hired
    */
    @FXML
    public void Refresh() {
        table.setItems(FXCollections.observableArrayList(getWorkers()));
    }

    /*
        Delete button deletes selected worker
        pressed when the manager wants to fire a worker
    */

    @FXML
    public void Delete() {
        User selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String mail = selected.getEmail();
            HotelDataBase.deleteWorker(mail);
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
