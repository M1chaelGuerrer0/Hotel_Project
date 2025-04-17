package com.example.demo9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ReservationController {
/*
    ImageView myImageView;
    Image myImage = new Image(getClass().getResourceAsStream("/com/example/demo9/suites.png"));
    public void displayImage(){
        myImageView.setImage(myImage);
    }

 */

    private Stage stage;
    private Scene scene;
    private Parent root;

     @FXML

    private DatePicker myDatePicker;

    @FXML

    private DatePicker myDatePicker2;








    /*
    @param myDatePicker - check in date
    @param myDatePicker2 - check out date
     */


/*
Prints the check-in date inputted by the user
 */
  public void getDate(ActionEvent event){
        LocalDate myDate = myDatePicker.getValue();
      String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
      System.out.println(myFormattedDate);




    }
    /*
    Prints the check-out date inputted by the user
     */
    public void getDate2(ActionEvent event){
        LocalDate myDate = myDatePicker2.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println(myFormattedDate);



    }





    /*
    This method returns the user back to the page where they choose their room
     */

    public void switchToScene1(ActionEvent event){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("scene1.fxml"));
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
    public void switchToScene2(ActionEvent event){

        try{
        FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("scene2.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /*
    In case the user is not logged in it will take them to a register page
     */

    public void switchToScene3(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("guestAddOn.fxml"));
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
