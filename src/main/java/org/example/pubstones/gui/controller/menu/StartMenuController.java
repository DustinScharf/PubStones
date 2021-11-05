package org.example.pubstones.gui.controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import org.example.pubstones.gui.controller.BaseController;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * Controller for the StartMenu
 */
public class StartMenuController extends BaseController {
    @FXML
    AnchorPane anchorPane;

    @Override
    public void init() {
        URL imageUrl;
        int current_day_hour = LocalDateTime.now().getHour();
        // Decide background by daytime
        if (current_day_hour >= 19 || current_day_hour < 7) {
            imageUrl = getClass().getResource("/gui/images/background/start_menu_night.jpg");
        } else {
            imageUrl = getClass().getResource("/gui/images/background/start_menu_day.jpg");
        }
        Image image = new Image(imageUrl.toString());  // TODO null check
        // the background fill is the background image
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        this.anchorPane.setBackground(new Background(backgroundFill));

        super.getManager().getMusicManager().setMusic("/music/menu/1.mp3"); // todo better music queueing

        super.getManager().getMusicManager().playMusic(); // start the music in an infinit loop
    }

    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        // stop the music because the next scene plays other music
        super.getManager().getMusicManager().stopMusic();
        super.getManager().getSceneManager().switchScene("/gui/fxml/game/Game.fxml");
    }

    @FXML
    public void settingsButtonClicked(ActionEvent actionEvent) {
        // music is not stopped here because the settings don't have extra music
        // to let the user configure a good volume for the normal music
        super.getManager().getSceneManager().switchScene("/gui/fxml/menu/SettingsMenu.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        // stops the music before the program exits
        super.getManager().getMusicManager().stopMusic();
        super.getManager().getSceneManager().getStage().close();
    }
}
