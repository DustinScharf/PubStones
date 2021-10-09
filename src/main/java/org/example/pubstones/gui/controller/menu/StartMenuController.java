package org.example.pubstones.gui.controller.menu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.pubstones.gui.controller.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController extends BaseController implements Initializable {
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = getClass().getResource("/music/menu/1.mp3").toString();
        Media media = new Media(path);
        this.mediaPlayer = new MediaPlayer(media);

        // TODO restart after scene reload
//        this.mediaPlayer.setVolume(0);
//        this.mediaPlayer.play();
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(3),
//                        new KeyValue(this.mediaPlayer.volumeProperty(), 1)));
//        timeline.play();
        this.musicManager.setMusic("/music/menu/1.mp3");
        this.musicManager.playMusic(3);
    }

    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        this.mediaPlayer.stop();
        super.sceneManager.switchScene("/gui/fxml/game/Game.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        this.mediaPlayer.stop();
        super.sceneManager.getStage().close();
    }
}
