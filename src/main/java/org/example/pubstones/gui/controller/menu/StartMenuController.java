package org.example.pubstones.gui.controller.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.example.pubstones.gui.controller.BaseController;

import java.net.URL;

public class StartMenuController extends BaseController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Slider volumeSlider;

    @Override
    public void init() {
//        URL imageUrl = getClass().getResource("/gui/images/background/start_menu_night.jpg");
//        Image image = new Image(imageUrl.toString(), 800, 600, false, true); // TODO null check
//        BackgroundImage backgroundImage = new BackgroundImage(
//                image,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT
//        );
//        anchorPane.setBackground(new Background(backgroundImage));

        super.getManager().getMusicManager().setMusic("/music/menu/1.mp3");
        super.getManager().getMusicManager().setVolume(this.volumeSlider.getValue());

        this.volumeSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            double sliderValue = newValue.doubleValue();
            getManager().getMusicManager().setVolume(sliderValue);
        });

        super.getManager().getMusicManager().playMusic();
    }

    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        super.getManager().getMusicManager().stopMusic();
        super.getManager().getSceneManager().switchScene("/gui/fxml/game/Game.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        super.getManager().getMusicManager().stopMusic();
        super.getManager().getSceneManager().getStage().close();
    }
}
