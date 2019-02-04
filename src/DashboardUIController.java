
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Create search Page, convert AdminUI to Movie Display/Output Page
 * Add Remaining ImageViews to the Gridpane
 * **/


public class DashboardUIController implements Initializable {
//    @FXML
//    private Menu AdminBtn;
//    @FXML
//    private MenuItem AddDropMovieBtn;
//    Stage stage = new Stage();
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private GridPane gp1;
    @FXML
    private GridPane gp2;
    @FXML
    private ImageView IV1,IV2,IV3,IV4,IV5,IV6,IV7,IV8,IV9,IV10,IV11,IV12,IV13,IV14,IV15,IV16,IV17,IV18,IV19,IV20,
            IV21,IV22,IV23,IV24,IV25,IV26,IV27,IV28,IV29,IV30,IV31,IV32,IV33,IV34,IV35,IV36,IV37,IV38, IV39,IV40;

    @FXML
    private TextField searchInputTF;

    private boolean hidden;
    String fileNameWithoutExtn = "src/pictures/";
    @FXML
    private Menu AdminBtn;

    String fileName;

    String MovieFileName;


    private LoginController login = new LoginController();
    private AdminUIController admin;

    //Database Connector

    private DBConnecter connecter = new DBConnecter();
    //Sliders list of movies/shows

    private ArrayList slider1 = new ArrayList();
    private ArrayList slider2 = new ArrayList();

    @FXML
    private GridPane gp;
    @FXML
    private Menu HomeLbl;
    @FXML
    private MenuItem dummyMenuItem;
    @FXML
    private Menu SearchLbl;
    @FXML
    private MenuItem SearchDummyMenuItem;

    @FXML
    private void fireDummyItem(Event event) {
        HomeLbl.hide();
        dummyMenuItem.fire();
    }



    @FXML
    private void fireSearchDummyItem(Event event) {
        SearchLbl.hide();
        SearchDummyMenuItem.fire();
    }


    @FXML
    private void SearchBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane = FXMLLoader.load(getClass().getResource("SearchUI.fxml"));
            pane.getStylesheets().add(getClass().getResource("dashboardCss.css").toExternalForm());
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateGP1();
        admin = new AdminUIController();
//        AdminBtn.setDisable(isAdmin());
//        AddDropMovieBtn.setDisable(isAdmin());
//        isAdmin();
    }


    @FXML
    private void HomeBtn_Clicked(Event mouseEvent) {

        System.out.println("Home btn Clicked");
        try {
            pane = FXMLLoader.load(getClass().getResource("DashboardUI.fxml"));
//            pane = FXMLLoader.load(getClass().getResource("AddDropUI.fxml"));
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void MoviesBtn_clicked(ActionEvent mouseEvent) {
        System.out.println("Movies btn Clicked");
        try {
            pane = FXMLLoader.load(getClass().getResource("MoviesUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    private void MyListBtn_Clicked(ActionEvent mouseEvent) {
        System.out.println("My List btn Clicked");
        try {
            pane = FXMLLoader.load(getClass().getResource("MyListUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    private void AdminBtn_Clicked() {
//        isAdmin();
        System.out.println("Admin btn Clicked");
        try {
            pane = FXMLLoader.load(getClass().getResource("AdminUI.fxml"));
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Admin Page and link the Add/Drop Page to it.
     *
     * @param mouseEvent*/
    @FXML
    private void ProfileBtn_Clicked(ActionEvent mouseEvent) {
        System.out.println("Profile btn Clicked");
        try {
            pane = FXMLLoader.load(getClass().getResource("ProfileUI.fxml"));
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void MyAccountBtn_Clicked(ActionEvent actionEvent) {
    }

    @FXML
    private void LogOutBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddDropMoviesBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane = FXMLLoader.load(getClass().getResource("AddDropUI.fxml"));
            dashboardPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void populateGP1() {
        System.out.println("loginBtn_Pressed");
//        String sql = "SELECT COUNT(*) AS count FROM Account WHERE AccountUsername = ? AND AccountPassword = ?;";
        String sql = "SELECT MovieTitle,MovieFile FROM Movie WHERE Hidden = 1 ORDER BY RANDOM() LIMIT 40";
        //SELECT * FROM table ORDER BY RANDOM() LIMIT X
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
//                int total = rs.getInt("count");
//                System.out.println(rs.getString("MovieTitle"));
                slider1.add(rs.getString("MovieTitle"));
                if (rs.getString("MovieFile") == null)
                {
                    slider2.add("dead.PNG");
                }
                else {
                    slider2.add(rs.getString("MovieFile"));
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("MovieTitle's: " + slider1);
        System.out.println("File Names: " + slider2);

        //Sets the Image from a file
        //slider.get(filelocation in array) is the file name
        File file = new File("src/pictures/"+slider2.get(0));
        Image image = new Image(file.toURI().toString());
        IV1.setImage(image);
        File file2 = new File("src/pictures/"+slider2.get(1));
        Image image2 = new Image(file2.toURI().toString());
        IV2.setImage(image2);
        File file3 = new File("src/pictures/" +slider2.get(2));
        Image image3 = new Image(file3.toURI().toString());
        IV3.setImage(image3);
        File file4 = new File("src/pictures/"+slider2.get(3));
        Image image4 = new Image(file4.toURI().toString());
        IV4.setImage(image4);
        File file5 = new File("src/pictures/"+slider2.get(4));
        Image image5 = new Image(file5.toURI().toString());
        IV5.setImage(image5);
        File file6 = new File("src/pictures/" +slider2.get(5));
        Image image6 = new Image(file6.toURI().toString());
        IV6.setImage(image6);
        File file7 = new File("src/pictures/"+slider2.get(6));
        Image image7 = new Image(file7.toURI().toString());
        IV7.setImage(image7);
        File file8 = new File("src/pictures/"+slider2.get(7));
        Image image8 = new Image(file8.toURI().toString());
        IV8.setImage(image8);
        File file9 = new File("src/pictures/"+slider2.get(8));
        Image image9 = new Image(file9.toURI().toString());
        IV9.setImage(image9);
        File file10 = new File("src/pictures/"+slider2.get(9));
        Image image10 = new Image(file10.toURI().toString());
        IV10.setImage(image10);
        File file11 = new File("src/pictures/"+slider2.get(10));
        Image image11 = new Image(file11.toURI().toString());
        IV11.setImage(image11);
        File file12 = new File("src/pictures/"+slider2.get(11));
        Image image12 = new Image(file12.toURI().toString());
        IV12.setImage(image12);
        File file13 = new File("src/pictures/"+slider2.get(12));
        Image image13 = new Image(file13.toURI().toString());
        IV13.setImage(image13);
        File file14 = new File("src/pictures/"+slider2.get(13));
        Image image14 = new Image(file14.toURI().toString());
        IV14.setImage(image14);
        File file15 = new File("src/pictures/"+slider2.get(14));
        Image image15 = new Image(file15.toURI().toString());
        IV15.setImage(image15);
        File file16 = new File("src/pictures/"+slider2.get(15));
        Image image16 = new Image(file16.toURI().toString());
        IV16.setImage(image16);
        File file17 = new File("src/pictures/"+slider2.get(16));
        Image image17 = new Image(file17.toURI().toString());
        IV17.setImage(image17);
        File file18 = new File("src/pictures/"+slider2.get(17));
        Image image18 = new Image(file18.toURI().toString());
        IV18.setImage(image18);
        File file19 = new File("src/pictures/"+slider2.get(18));
        Image image19 = new Image(file19.toURI().toString());
        IV19.setImage(image19);
        File file20 = new File("src/pictures/"+slider2.get(19));
        Image image20 = new Image(file20.toURI().toString());
        IV20.setImage(image20);
        File file21 = new File("src/pictures/"+slider2.get(20));
        Image image21 = new Image(file21.toURI().toString());
        IV21.setImage(image21);
        File file22 = new File("src/pictures/"+slider2.get(21));
        Image image22 = new Image(file22.toURI().toString());
        IV22.setImage(image22);
        File file23 = new File("src/pictures/"+slider2.get(22));
        Image image23 = new Image(file23.toURI().toString());
        IV23.setImage(image23);
        File file24 = new File("src/pictures/"+slider2.get(23));
        Image image24 = new Image(file24.toURI().toString());
        IV24.setImage(image24);
        File file25 = new File("src/pictures/"+slider2.get(24));
        Image image25 = new Image(file25.toURI().toString());
        IV25.setImage(image25);
        File file26 = new File("src/pictures/"+slider2.get(25));
        Image image26 = new Image(file26.toURI().toString());
        IV26.setImage(image26);
//        File file27 = new File("src/pictures/"+slider2.get(26));
//        Image image27 = new Image(file27.toURI().toString());
//        IV27.setImage(image27);
//        File file28 = new File("src/pictures/"+slider2.get(27));
//        Image image28 = new Image(file28.toURI().toString());
//        IV28.setImage(image28);
//        File file29 = new File("src/pictures/"+slider2.get(28));
//        Image image29 = new Image(file29.toURI().toString());
//        IV29.setImage(image29);
//        File file30 = new File("src/pictures/"+slider2.get(29));
//        Image image30 = new Image(file30.toURI().toString());
//        IV30.setImage(image30);
//        File file31 = new File("src/pictures/"+slider2.get(30));
//        Image image31 = new Image(file31.toURI().toString());
//        IV31.setImage(image31);
//        File file32 = new File("src/pictures/"+slider2.get(31));
//        Image image32 = new Image(file32.toURI().toString());
//        IV32.setImage(image32);
//        File file33 = new File("src/pictures/"+slider2.get(32));
//        Image image33 = new Image(file33.toURI().toString());
//        IV33.setImage(image33);
//        File file34 = new File("src/pictures/"+slider2.get(33));
//        Image image34 = new Image(file34.toURI().toString());
//        IV34.setImage(image34);
//        File file35 = new File("src/pictures/"+slider2.get(34));
//        Image image35 = new Image(file35.toURI().toString());
//        IV35.setImage(image35);
//        File file36 = new File("src/pictures/"+slider2.get(35));
//        Image image36 = new Image(file36.toURI().toString());
//        IV36.setImage(image36);
//        File file37 = new File("src/pictures/"+slider2.get(36));
//        Image image37 = new Image(file37.toURI().toString());
//        IV37.setImage(image37);
//        File file38 = new File("src/pictures/"+slider2.get(37));
//        Image image38 = new Image(file38.toURI().toString());
//        IV38.setImage(image38);
//        File file39 = new File("src/pictures/"+slider2.get(38));
//        Image image39 = new Image(file39.toURI().toString());
//        IV39.setImage(image39);
//        File file40 = new File("src/pictures/"+slider2.get(39));
//        Image image40 = new Image(file40.toURI().toString());
//        IV40.setImage(image40);
    }


    public void populateGP2() {

    }

    public boolean isAdmin() {
        String sql = "SELECT Admin FROM Account WHERE AccountUsername = ? AND AccountPassword = ?;";
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            pstmt.setString(1, login.getUsernameTF().getText());
            pstmt.setString(2, login.getPasswordTF().getText());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hidden = rs.getBoolean("Admin");
//                if (!rs.getBoolean("Admin")) {
//                    AdminBtn.hide();
//
//                }
//                else {
//                    AdminBtn.show();
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hidden;
    }

    @FXML
    private void IV1setOnMousePressed() {
        IV1.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();

//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV1.getImage().getUrl().substring(IV1.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
//            AdminBtn_Clicked();
        });
    }

    @FXML
    private void IV2setOnMousePressed() {
        IV2.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();

//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV2.getImage().getUrl().substring(IV2.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
//            AdminBtn_Clicked();
        });
    }
    @FXML
    private void IV3setOnMousePressed() {
        IV3.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV3.getImage().getUrl().substring(IV3.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
//            AdminBtn_Clicked();
        });
    }
    @FXML
    private void IV4setOnMousePressed() {
        IV4.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV4.getImage().getUrl().substring(IV4.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
//            AdminBtn_Clicked();
        });}
    @FXML
    private void IV5setOnMousePressed() {
        IV5.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV5.getImage().getUrl().substring(IV5.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV6setOnMousePressed() {
        IV6.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV6.getImage().getUrl().substring(IV6.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV7setOnMousePressed() {
        IV7.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV7.getImage().getUrl().substring(IV7.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV8setOnMousePressed() {
        IV8.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV8.getImage().getUrl().substring(IV8.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV9setOnMousePressed() {
        IV9.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV9.getImage().getUrl().substring(IV9.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV10setOnMousePressed() {
        IV10.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV10.getImage().getUrl().substring(IV10.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV11setOnMousePressed() {
        IV11.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV11.getImage().getUrl().substring(IV11.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV12setOnMousePressed() {
        IV12.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV12.getImage().getUrl().substring(IV12.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV13setOnMousePressed() {
        IV13.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV13.getImage().getUrl().substring(IV13.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV14setOnMousePressed() {
        IV14.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV14.getImage().getUrl().substring(IV14.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV15setOnMousePressed() {
        IV15.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV15.getImage().getUrl().substring(IV15.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV16setOnMousePressed() {
        IV16.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV16.getImage().getUrl().substring(IV16.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV17setOnMousePressed() {
        IV17.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV17.getImage().getUrl().substring(IV17.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV18setOnMousePressed() {
        IV18.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV18.getImage().getUrl().substring(IV18.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV19setOnMousePressed() {
        IV19.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV19.getImage().getUrl().substring(IV19.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV20setOnMousePressed() {
        IV20.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV20.getImage().getUrl().substring(IV20.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV21setOnMousePressed() {
        IV21.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV21.getImage().getUrl().substring(IV21.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV22setOnMousePressed() {
        IV22.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV22.getImage().getUrl().substring(IV22.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV23setOnMousePressed() {
        IV23.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV23.getImage().getUrl().substring(IV23.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV24setOnMousePressed() {
        IV24.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV24.getImage().getUrl().substring(IV24.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV25setOnMousePressed() {
        IV25.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV25.getImage().getUrl().substring(IV25.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV26setOnMousePressed() {
        IV26.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV26.getImage().getUrl().substring(IV26.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV27setOnMousePressed() {
        IV27.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV27.getImage().getUrl().substring(IV27.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV28setOnMousePressed() {
        IV28.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV28.getImage().getUrl().substring(IV28.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV29setOnMousePressed() {
        IV29.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV29.getImage().getUrl().substring(IV29.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV30setOnMousePressed() {
        IV30.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV30.getImage().getUrl().substring(IV30.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV31setOnMousePressed() {
        IV31.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV31.getImage().getUrl().substring(IV31.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV32setOnMousePressed() {
        IV32.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV32.getImage().getUrl().substring(IV32.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV33setOnMousePressed() {
        IV33.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV33.getImage().getUrl().substring(IV33.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV34setOnMousePressed() {
        IV34.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV34.getImage().getUrl().substring(IV34.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV35setOnMousePressed() {
        IV35.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV35.getImage().getUrl().substring(IV35.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV36setOnMousePressed() {
        IV36.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV36.getImage().getUrl().substring(IV36.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV37setOnMousePressed() {
        IV37.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV37.getImage().getUrl().substring(IV37.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV38setOnMousePressed() {
        IV38.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV38.getImage().getUrl().substring(IV38.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV39setOnMousePressed() {
        IV39.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV39.getImage().getUrl().substring(IV39.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }
    @FXML
    private void IV40setOnMousePressed() {
        IV40.setOnMousePressed(event -> {
            String source2 = event.getPickResult().getIntersectedNode().getId();
            System.out.println("Clicked: " +source2);
//            IV1.setEffect(new DropShadow(20, Color.CRIMSON));
            fileName = IV40.getImage().getUrl().substring(IV40.getImage().getUrl().lastIndexOf('/')+1);
            //fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println("Clicked: " +source2 + " FileName: " + fileName);
            searchForMovieFile(fileName);
            AdminBtn.fire();
        });
    }

    @FXML
    private void setOnMouseReleased(MouseEvent mouseEvent) {
    }

    private String searchForMovieFile(String fileName) {
        String videoFile = "";
        String sql = "SELECT VideoFile FROM Movie WHERE MovieFile = ?";
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            pstmt.setString(1, fileName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                videoFile = rs.getString("VideoFile");

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(videoFile);
//        admin.setMovieFileName(videoFile);
//        admin.
        AdminUIController.MovieFileName = videoFile;
     return videoFile;
    }



}
