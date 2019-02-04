import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private AnchorPane rootPane;
    private DBConnecter connecter = new DBConnecter();

    @FXML
    private Label lblLoginFail;
    @FXML
    private void loginBtn_Pressed(ActionEvent actionEvent) {

        System.out.println("loginBtn_Pressed");
        String sql = "SELECT COUNT(*) AS count FROM Account WHERE AccountUsername = ? AND AccountPassword = ?;";
        try {
            PreparedStatement pstmt = connecter.connect().prepareStatement(sql);
            pstmt.setString(1, usernameTF.getText());
            pstmt.setString(2, passwordTF.getText());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 int total = rs.getInt("count");
                System.out.println(total);
                 if (total == 1) {
                     System.out.println("Login Pass");
                     //Loads next scene into the window
                     lblLoginFail.setVisible(false);
//                     AnchorPane pane = FXMLLoader.load(getClass().getResource("SelectProfileUI.fxml"));
                     AnchorPane pane = FXMLLoader.load(getClass().getResource("DashboardUI.fxml"));
                     pane.getStylesheets().add(getClass().getResource("dashboardCss.css").toExternalForm());
                     rootPane.getChildren().setAll(pane);
                 }
                 else {
                     System.out.println("Login Fail");
                     lblLoginFail.setVisible(true);
                 }
            }
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public TextField getUsernameTF() {
        return usernameTF;
    }

    private void setUsernameTF(TextField usernameTF) {
        this.usernameTF = usernameTF;
    }

    public PasswordField getPasswordTF() {
        return passwordTF;
    }

    private void setPasswordTF(PasswordField passwordTF) {
        this.passwordTF = passwordTF;
    }

    @FXML
    private void createAccountBtn_Pressed(ActionEvent actionEvent) {
        System.out.println("createAccountBtn_Pressed");
        try {
             AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateProfileUI.fxml"));
             rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void oneTimeBtn_Pressed(ActionEvent actionEvent) {
        System.out.println("oneTimeBtn_Pressed");
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("OneTimeUserUI.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
