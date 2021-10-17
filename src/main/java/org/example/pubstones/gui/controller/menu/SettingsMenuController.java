package org.example.pubstones.gui.controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * Controller for the SettingsMenu
 */
public class SettingsMenuController extends BaseController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    public TextField nameTextField;

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
        this.anchorPane.setBackground(new Background(backgroundFill));

        this.nameTextField.setText(super.getManager().getUserSettings().getName());

        this.volumeSlider.setValue(super.getManager().getMusicManager().getVolume());

        this.volumeSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            double sliderValue = newValue.doubleValue();
            getManager().getMusicManager().setVolume(sliderValue);
        });
    }

    @FXML
    public void backButtonClicked(ActionEvent actionEvent) {
        UserSettings userSettings = new UserSettings("default_settings");
        userSettings.setName(this.nameTextField.getText());
        userSettings.setVolume(super.getManager().getMusicManager().getVolume());
        super.getManager().setUserSettings(userSettings);
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream("userdata/" + userSettings.getSettingsName()))) {
            objectOutputStream.writeObject(userSettings);
            objectOutputStream.flush();
        } catch (IOException e) {
            System.err.println("File not found");
            e.printStackTrace(); // TODO
        }

        try {
            super.getManager().getSceneManager().switchToPreviousScene();
        } catch (OutOfTimelineException e) {
            e.printStackTrace(); // TODO
        }
    }
}
