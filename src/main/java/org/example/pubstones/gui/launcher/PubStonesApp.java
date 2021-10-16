package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.manager.Manager;
import org.example.pubstones.gui.util.Alerter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class PubStonesApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PubStones");
        // TODO null check
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/icons/main_icon.png")));

        Manager manager = new Manager(primaryStage);

        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream("userdata/custom_settings"))) {
            UserSettings userSettings = (UserSettings) objectInputStream.readObject();
            manager.applyUserSettings(userSettings);
        } catch (FileNotFoundException fileNotFoundException) {
            Alerter.buildInfoAlert(
                    "Welcome to PubStones!",
                    "Seems like this is the first time you start PubStones!\n" +
                            "We loaded the default settings for you, you can changed them in the settings menu later..."
            ).showAndWait();
        }

        manager.getSceneManager().switchScene("/gui/fxml/menu/StartMenu.fxml");

        primaryStage.show();
    }
}
