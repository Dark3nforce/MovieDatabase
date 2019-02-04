import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchUIController {
    @FXML
    private Menu HomeLbl;
    @FXML
    private MenuItem dummyMenuItem;
    @FXML
    private AnchorPane searchPane;

    private AnchorPane pane2;
    @FXML
    private TextField searchInputTF;

    private String input;
    private DBConnecter connecter = new DBConnecter();

    private ArrayList<String> slider3 = new ArrayList<String>();
    private ArrayList<String> slider4 = new ArrayList<String>();
    private ArrayList slider5 = new ArrayList();
    private ArrayList<String> slider6 = new ArrayList<String>();
    @FXML
    private Label MovieColumnLbl;
    @FXML
    private Label ActorsColumnLbl;
    @FXML
    private Label GenreColumnLbl;


//    @FXML
//    private void submitBtn_Clicked(ActionEvent actionEvent) {
//        System.out.println("submitBtn_Pressed");
//
////        if (hiddenCB.isSelected()) {
////            hidden = true;
////        }
////        else {
////            hidden = false;
////        }
////
//////        String sql = "SELECT COUNT(*) AS count FROM Account WHERE AccountUsername = ? AND AccountPassword = ?;";
////        String sql = "INSERT INTO Movie(MovieTitle,AvailableCountries,YearReleased,Hidden) VALUES(?,?,?,?)";
////        try {
////            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
////            pstmt.setString(1, inputTitleTF.getText());
////            pstmt.setString(2, inputACTF.getText());
////            pstmt.setString(3, inputYearReleasedTF.getText());
////            pstmt.setBoolean(4, hidden);
////            pstmt.executeUpdate();
//////            ResultSet rs = pstmt.executeQuery();
//////            while (rs.next()) {
//////
//////            }
////        }
////        catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }

    @FXML
    private void fireDummyItem(Event event) {
        HomeLbl.hide();
        dummyMenuItem.fire();
    }

    @FXML
    private void HomeBtn2_Clicked(Event event) {
        System.out.println(event);
        System.out.println("Home btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("DashboardUI.fxml"));
            searchPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void MoviesBtn_clicked(ActionEvent actionEvent) {
        System.out.println("Movies btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("MoviesUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchPane.getChildren().setAll(pane2);
    }

    @FXML
    private void MyListBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("My List btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("MyListUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchPane.getChildren().setAll(pane2);
    }

    @FXML
    private void MyAccountBtn_Clicked(ActionEvent actionEvent) {

    }

    @FXML
    private void ProfileBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("Profile btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("ProfileUI.fxml"));
            searchPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AdminBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("Admin btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("AdminUI.fxml"));
            searchPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddDropMoviesBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane2 = FXMLLoader.load(getClass().getResource("AddDropUI.fxml"));
            searchPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LogOutBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane2 = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
            searchPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void SearchBar_Activated() {
        String Actors = "";
        String Movies = "";
        String Genres = "";
        ActorsColumnLbl.setText("");
        MovieColumnLbl.setText("");
        GenreColumnLbl.setText("");

        input = searchInputTF.getText();
        Search(input);
//        searchActorLastName(input);
//        searchActorFirstName(input);
//        searchGenreName(input);
        input="";
        searchInputTF.clear();
//        while (!slider3.get(0).equals(slider3.get(1))) {
//            MovieColumnLbl.setText(slider3.get(0).toString());
//        }
        for (int i = 0; i < slider3.size(); i++) {
            if (slider3.get(i).equals(slider3.get(i+1))) {
//                MovieColumnLbl.setText(slider3.get(0));
                Movies += slider3.get(i) + "\n";
                break;
            }
        }
        for (int i = 0; i < slider6.size(); i++) {
            if (slider6.get(i).equals(slider6.get(i+1))) {
                Genres += slider6.get(i) + "\n";
//                GenreColumnLbl.setText(slider6.get(0));
                break;
            }
        }
        for (int i = 0; i < slider4.size(); i++) {
//            ActorsColumnLbl.setText(slider4.get(i) + "\n");
            Actors += slider4.get(i) + "\n";
        }
        ActorsColumnLbl.setText(Actors);
        MovieColumnLbl.setText(Movies);
        GenreColumnLbl.setText(Genres);
        slider3.clear();
        slider4.clear();
        slider6.clear();

    }
    private void Search(String input) {
        String sql = "SELECT DISTINCT MovieTitle,FirstName,LastName,GenreName FROM Movie INNER JOIN Actors_Movie on Movie.MovieID = Actors_Movie.MovieID INNER JOIN Actor on Actor.ActorID = Actors_Movie.ActorID INNER JOIN Genre on Movie.MovieID = Genre.MovieID WHERE MovieTitle LIKE ? OR FirstName LIKE ? OR LastName LIKE ? OR GenreName LIKE ?;";
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            System.out.println(input);
            pstmt.setString(1, input);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                slider3.add(rs.getString("MovieTitle"));
                slider4.add(rs.getString("FirstName") + " " +rs.getString("LastName"));
                slider6.add(rs.getString("GenreName"));

            }
            System.out.println("MovieTitle " +slider3);
            System.out.println("FirstName " +slider4);
//            System.out.println("LastName " +slider5);
            System.out.println("GenreName " +slider6);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void searchGenreName(String input) {
//        String sql = "SELECT GenreName FROM Genre WHERE GenreName LIKE ?";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
//            pstmt.setString(1, searchInputTF.getText());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                slider6.add(rs.getString("GenreName"));
//                System.out.println(slider6);
//
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void searchActorFirstName(String input) {
//        String sql = "SELECT FirstName FROM Actor WHERE FirstName LIKE ?";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
//            pstmt.setString(1, searchInputTF.getText());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                slider4.add(rs.getString("FirstName"));
//                System.out.println(slider4);
//
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void searchActorLastName(String input) {
//        String sql = "SELECT LastName FROM Actor WHERE LastName LIKE ?";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
//            pstmt.setString(1, searchInputTF.getText());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                slider5.add(rs.getString("LastName"));
//                System.out.println(slider5);
//
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void searchMovie(String input) {
//        String sql = "SELECT MovieTitle FROM Movie WHERE MovieTitle Like ?";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
//            pstmt.setString(1, searchInputTF.getText());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                slider3.add(rs.getString("MovieTitle"));
//                System.out.println(slider3);
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}
