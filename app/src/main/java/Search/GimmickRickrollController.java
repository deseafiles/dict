package Search;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class GimmickRickrollController {

    @FXML
    private MediaView mediaView;

    public void initialize() {
        String videoPath = "D:\\Cari Tahu/Rickroll.mp4"; 
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.play();
    }
}