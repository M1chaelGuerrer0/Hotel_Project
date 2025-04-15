package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountController {

    @FXML
    private AnchorPane accountScene;

    @FXML
    private Button cancel;

    @FXML
    private Label emailDisplay;

    @FXML
    private Label passwordDisplay;

    @FXML
    private Label address1Display;

    @FXML
    private Label address2Display;

    @FXML
    private Label stateDisplay;

    @FXML
    private Label countryDisplay;

    @FXML
    private Label cityDisplay;

    private Parent root;

    private Scene scene;

    private Stage stage;


    public void cancelButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
