import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PickProfileController {

    @FXML
    public Button addProfile;

    @FXML
    public Button removeProfile;

    @FXML
    public AnchorPane rootPane;




    @FXML
    public void addBtn_Pressed(ActionEvent actionEvent) {
        System.out.println("Add button pressed!");
        System.out.println("Going to Create Profile Page");

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateProfileUI.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //adds a profile and updates the database

    }
    @FXML
    public void removeBtn_Pressed(ActionEvent actionEvent){

        //remove a profile and updates the database
        //.remove

    }

}
