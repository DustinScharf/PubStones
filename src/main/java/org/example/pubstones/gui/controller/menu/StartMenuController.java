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

public class StartMenuController extends BaseController {
    @Override
    public void init() {
        super.musicManager.setMusic("/music/menu/1.mp3");
        super.musicManager.playMusic(3);
    }

    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        super.musicManager.stopMusic(1);
        super.sceneManager.switchScene("/gui/fxml/game/Game.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        super.musicManager.stopMusic(0);
        super.sceneManager.getStage().close();
    }
}
