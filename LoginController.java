package com.example.gooncentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML
    private AnchorPane loginScene;

    @FXML
    private Button continueAsGuest;

    @FXML
    private Button forgotPassword;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField user;

    @FXML
    private TextField password;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public static Boolean logStatus = false;

    public void continueAsGuestButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerLog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginButton(ActionEvent event) throws IOException {
        logStatus = true;

        root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println(logStatus);
    }

    public void closeLog(ActionEvent event) {
        stage = (Stage) loginScene.getScene().getWindow();
        stage.close();
    }


}