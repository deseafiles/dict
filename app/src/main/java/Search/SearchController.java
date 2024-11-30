package Search;
package org.example.searchengine;

import javax.print.attribute.standard.Media;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SearchController {

    @FXML
    private Button handleSearch;

    @FXML
    private MediaView mediaView;

    public void initialize() {
        String videoPath = "D:\\Cari Tahu/Thanos.mp4"; 
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        
        mediaPlayer.play();
    }

    @FXML
    private ListView<?> resultList;

    @FXML
    private TextField searchBox;

    @FXML
    private ListView<?> suggestionList;

}

