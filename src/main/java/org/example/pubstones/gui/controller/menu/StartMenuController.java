package org.example.pubstones.gui.controller.menu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.pubstones.gui.controller.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController extends BaseController {
    @Override
    public void init() {
        KeyCombination controlMKeyCombination = new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN);
        super.getManager().getSceneManager().getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if(controlMKeyCombination.match(event)){
                if (super.getManager().getMusicManager().isMuted()) {
                    super.getManager().getMusicManager().unMuteMusic(3);
                } else {
                    super.getManager().getMusicManager().muteMusic(1);
                }
            }
            event.consume();
        });

        super.getManager().getMusicManager().setMusic("/music/menu/1.mp3");
        super.getManager().getMusicManager().playMusic(3);
    }

    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        super.getManager().getMusicManager().stopMusic(1);
        super.getManager().getSceneManager().switchScene("/gui/fxml/game/Game.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        super.getManager().getMusicManager().stopMusic(0);
        super.getManager().getSceneManager().getStage().close();
    }
}
