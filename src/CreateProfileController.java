import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.PreparedStatement;

public class CreateProfileController {


    @FXML
    public TextField EnterName;
    @FXML
    public AnchorPane rootPane;

    @FXML
    public ImageView alien;
    public ImageView happy;



    @FXML
    public void image_Presssed(ActionEvent actionEvent){


    }


    @FXML
    public void createBtn_Pressed(ActionEvent actionEvent) {

        System.out.print("\nCreate button pushed");
        String name = EnterName.getText();
        System.out.print("\n" + name);

       //create an insert statement to insert name into the Profile & updates the Profile
        if (name.isEmpty()) {
            System.out.print("Please enter a name: ");
        }
        else {
            System.out.print("\nGoing to Profile Page");

        }






    }


    @FXML
    private void image_Pressed(MouseEvent mouseEvent) {
    }
}
