package Pages.CreateAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountUI
{

    @FXML
    public AnchorPane rootPane;

    @FXML
    private ToggleGroup plansTG;
    @FXML
    private Image plan1img = new Image( getClass().getResourceAsStream("_plan1.jpg") );
    @FXML
    private Image plan2img = new Image( getClass().getResourceAsStream("_plan2.jpg") );
    @FXML
    private Image plan3img = new Image( getClass().getResourceAsStream("_plan3.jpg") );
    ///////// Do I cast these as ImageViews???

    public void RadioButtons()
    {
        ToggleGroup plansTG = new ToggleGroup();

        //// Example of setting an image to a radio button:
        //    Image image = new Image(getClass().getResourceAsStream("ok.jpg"));
        //    RadioButton rb = new RadioButton("Agree");
        //      rb.setGraphic(new ImageView(image));

        RadioButton rb1 = new RadioButton("Plan1");
        rb1.setToggleGroup(plansTG);
        rb1.setSelected(true);
        rb1.setGraphic(new ImageView("src/pictures/"+plan1img));


        RadioButton rb2 = new RadioButton("Plan 2");
        rb2.setToggleGroup(plansTG);
        rb2.setGraphic(new ImageView("src/pictures/"+plan2img));



        RadioButton rb3 = new RadioButton("Plan 3");
        rb3.setToggleGroup(plansTG);
        rb3.setGraphic(new ImageView("src/pictures/"+plan3img));


    }




////////// It shouldn't need a main... right?
//    public static void main(String[] args)
//    {
//        plansToggleGroup.add(rb1);
//
//        rb1.setToggleGroup(plansToggleGroup);
//        rb1.setSelected(true);
//
//        RadioButton rb2 = new RadioButton("Plan 2");
//        rb2.setToggleGroup(group);
//
//        RadioButton rb3 = new RadioButton("");
//        rb3.setToggleGroup(group);
//
//
//    }






    public void submitBtn_Pressed(ActionEvent actionEvent)
    {
        System.out.println("submitButton_Pressed");
        // test for the agree box to be checked
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Pages/CreateProfile/CreateProfileUI.fxml"));
            pane.getStylesheets().add(getClass().getResource("CreateProfileUICss.css").toExternalForm());
            rootPane.getChildren().setAll(pane);

            /////////// add handling for user information to be stored in the database
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }











}
