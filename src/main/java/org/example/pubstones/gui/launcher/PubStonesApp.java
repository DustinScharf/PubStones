package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.manager.Manager;
import org.example.pubstones.gui.util.Alerter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class launches the whole program
 */
public class PubStonesApp extends Application {
    /**
     * Entry point for gui
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // sets the title of the window corresponding to the game title
        primaryStage.setTitle("PubStones");
        // TODO null check
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/icons/main_icon.png")));

        Manager manager = new Manager(primaryStage);
        // TODO put file path into own final static variable
        // checks if there are settings and if so loads them
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream("userdata/default_settings"))) {
            UserSettings userSettings = (UserSettings) objectInputStream.readObject();
            manager.setUserSettings(userSettings);
        } catch (FileNotFoundException fileNotFoundException) {
            // if no settings are found, the game copies the default settings and sets them as the user settings
            Alerter.buildInfoAlert(
                    "Welcome to PubStones!",
                    "Seems like this is the first time you start PubStones!\n" +
                            "We loaded the default settings for you, you can changed them in the settings menu later..."
            ).showAndWait();
            this.loadDefaultSettings(manager);
        }

        // switches immediately to start menu on start
        manager.getSceneManager().switchScene("/gui/fxml/menu/StartMenu.fxml");

        // shows the window after everything is donw
        primaryStage.show();
    }

    /**
     * Loads the default settings
     *
     * @param manager manager to load to settings to
     */
    private void loadDefaultSettings(Manager manager) {
        UserSettings defaultUserSettings = null;
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(getClass().getResourceAsStream("/gui/settings/default_settings"))) {
            defaultUserSettings = (UserSettings) objectInputStream.readObject();
            manager.setUserSettings(defaultUserSettings);
        } catch (Exception ignored) {
            // should be downloaded or stuff
            System.err.println("INTERN ERROR: THERE ARE NO DEFAULT SETTINGS");
            System.exit(1);
        }

        // .substring(6) removes "file:/" generated from maven, because this does not work with java file class
        File source = new File(getClass().getResource("/gui/settings/default_settings").toString().substring(6));
        System.out.println(source);
        File destination = new File("userdata/" + defaultUserSettings.getSettingsName());
        try {
            // copies the default settings file to the user settings directory (if the user has no settings)
            FileUtils.copyFile(source, destination);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
