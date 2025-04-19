package com.example.demo9;
/*
This is the class that runs the code.
@E.Sanchez
@version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;




/*

 */
public class ReservationApplication extends Application {
    /*
    This runs the reservation page
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 812, 642);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}