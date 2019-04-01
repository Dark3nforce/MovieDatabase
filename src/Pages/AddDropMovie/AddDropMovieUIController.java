package Pages.AddDropMovie;

import Res.DBConnecter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddDropMovieUIController implements Initializable {
    DBConnecter connecter = new DBConnecter();
    @FXML
    private TextField inputTitleTF;
    @FXML
    private TextField inputYearReleasedTF;
    @FXML
    private TextField inputACTF;
    @FXML
    private CheckBox hiddenCB;
    @FXML
    private AnchorPane pane2;

    @FXML
    private MenuItem dummyMenuItem;

    Stage stage = new Stage();


    private boolean hidden;
    private ObservableList<ObservableList> data;
//    @FXML
//    private ChoiceBox tableDisplayCB;
    @FXML
    private TableView movieTVDisplay;
    @FXML
    private AnchorPane AddDropPane;
    @FXML
    private Menu HomeLbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        String sql = "SELECT tbl_name FROM sqlite_master";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                tableDisplayCB.getItems().add(rs.toString());
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }


//        data = FXCollections.observableArrayList();
//        String sql2 = "SELECT * FROM Movie";
//        try {
//            PreparedStatement pstmt = connecter.connect().prepareStatement(sql2);
////            pstmt.setString(1, tableDisplayCB.getItems().toString());
//
//            ResultSet rs = pstmt.executeQuery();
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                movieTVDisplay.getColumns().addAll(col);
////                System.out.println("Column [" + i + "] ");
//            }
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added "+row );
//                data.add(row);
//            }
//            movieTVDisplay.setItems(data);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    @FXML
    private void submitBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("submitBtn_Pressed");
        if (hiddenCB.isSelected()) {
            hidden = true;
        }
        else {
            hidden = false;
        }

//        String sql = "SELECT COUNT(*) AS count FROM Account WHERE AccountUsername = ? AND AccountPassword = ?;";
        String sql = "INSERT INTO Movie(MovieTitle,AvailableCountries,YearReleased,Hidden) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            pstmt.setString(1, inputTitleTF.getText());
            pstmt.setString(2, inputACTF.getText());
            pstmt.setString(3, inputYearReleasedTF.getText());
            pstmt.setBoolean(4, hidden);
            pstmt.executeUpdate();
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fireDummyItem(Event event) {

        dummyMenuItem.fire();
    }

    @FXML
    private void HomeBtn2_Clicked(Event event) {
        System.out.println(event);
        System.out.println("Home btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("Pages/Dashboard/DashboardUI.fxml"));
            AddDropPane.getChildren().setAll(pane2);
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
        AddDropPane.getChildren().setAll(pane2);
    }

    @FXML
    private void MyListBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("My List btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("MyListUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddDropPane.getChildren().setAll(pane2);
    }

    @FXML
    private void MyAccountBtn_Clicked(ActionEvent actionEvent) {

    }

    @FXML
    private void ProfileBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("Profile btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("ProfileUI.fxml"));
            AddDropPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AdminBtn_Clicked(ActionEvent actionEvent) {
        System.out.println("Pages.Admin btn Clicked");
        try {
            pane2 = FXMLLoader.load(getClass().getResource("Pages/Admin/AdminUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddDropPane.getChildren().setAll(pane2);
    }

    @FXML
    private void AddDropMoviesBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane2 = FXMLLoader.load(getClass().getResource("Pages/AddDropMovie/AddDropUI.fxml"));
            AddDropPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LogOutBtn_Clicked(ActionEvent actionEvent) {
        try {
            pane2 = FXMLLoader.load(getClass().getResource("Pages/Login/LoginUI.fxml"));
            AddDropPane.getChildren().setAll(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
