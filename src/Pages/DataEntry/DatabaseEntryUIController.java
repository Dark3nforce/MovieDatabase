package Pages.DataEntry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseEntryUIController {

    private Connection connect() {
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:/src/Res/MovieDB";
            con = DriverManager.getConnection(url);
//            System.out.println("Database Connected!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }


    public static void main(String[] args) {

    }
}
