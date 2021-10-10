package org.example.pubstones.gui.controller.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import org.example.pubstones.gui.controller.BaseController;

public class StartMenuController extends BaseController {
    @FXML
    Slider volumeSlider;

    @Override
    public void init() {
        super.getManager().getMusicManager().setMusic("/music/menu/1.mp3");
        super.getManager().getMusicManager().setVolume(this.volumeSlider.getValue());

        this.volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                double sliderValue = newValue.doubleValue();
                System.out.println(sliderValue);
                getManager().getMusicManager().setVolume(sliderValue);
            }
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
