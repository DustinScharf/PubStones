package org.example.pubstones.gui.controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import org.example.pubstones.gui.controller.BaseController;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class StartMenuController extends BaseController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Slider volumeSlider;

    @Override
    public void init() {
        URL imageUrl;
        int current_day_hour = LocalDateTime.now().getHour();
        if (current_day_hour >= 19 || current_day_hour < 7) {
            imageUrl = getClass().getResource("/gui/images/background/start_menu_night.jpg");
        } else {
            imageUrl = getClass().getResource("/gui/images/background/start_menu_day.jpg");
        }
        Image image = new Image(imageUrl.toString());  // TODO null check
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        anchorPane.setBackground(new Background(backgroundFill));

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
