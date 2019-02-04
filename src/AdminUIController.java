import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AdminUIController implements Initializable {

    @FXML
    private AnchorPane adminPane;
    private AnchorPane pane;
    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    Slider volumeSlider;

    public  static String MovieFileName;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        DashboardUIController dashboard = new DashboardUIController();
//        String movieFile = getMovieFileName();
//        System.out.println(movieFile);
        System.out.println(getMovieFileName());
        String path = new File("src/Movies/" + MovieFileName).getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);

        volumeSlider.setValue(mp.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> mp.setVolume(volumeSlider.getValue() / 100));
    }
    @FXML
    public void play(ActionEvent actionEvent){
        mp.play();
        mp.setRate(1);
    }
    public void pause(ActionEvent actionEvent){

        mp.pause();
    }
    public void start(ActionEvent actionEvent){
        mp.seek(mp.getStartTime());
        mp.stop();
    }
    public void last(ActionEvent actionEvent){
        mp.seek(mp.getTotalDuration());
        mp.stop();
    }
    public void maximize(ActionEvent actionEvent){
         DoubleProperty width = mv.fitWidthProperty();
         DoubleProperty height = mv.fitHeightProperty();
         width.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
         height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
    }
    @FXML
    private void addDropLbl_Clicked(MouseEvent mouseEvent) {
        try {
            pane = FXMLLoader.load(getClass().getResource("AddDropUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminPane.getChildren().setAll(pane);
    }
    public void setMovieFileName(String movieFileName) {
        MovieFileName = movieFileName;
    }
    public String getMovieFileName() {
        return MovieFileName;
    }
}
