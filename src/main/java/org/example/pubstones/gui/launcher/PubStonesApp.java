package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.manager.Manager;
import org.example.pubstones.gui.util.Alerter;

import java.io.*;

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
                     new ObjectInputStream(new FileInputStream("userdata/default_settings"))) {
            UserSettings userSettings = (UserSettings) objectInputStream.readObject();
            manager.setUserSettings(userSettings);
        } catch (FileNotFoundException fileNotFoundException) {
            Alerter.buildInfoAlert(
                    "Welcome to PubStones!",
                    "Seems like this is the first time you start PubStones!\n" +
                            "We loaded the default settings for you, you can changed them in the settings menu later..."
            ).showAndWait();
            this.loadDefaultSettings(manager);
        }

        manager.getSceneManager().switchScene("/gui/fxml/menu/StartMenu.fxml");

        primaryStage.show();
    }

    private void loadDefaultSettings(Manager manager) {
        UserSettings defaultUserSettings = null;
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(getClass().getResourceAsStream("/gui/settings/default_settings"))) {
            defaultUserSettings = (UserSettings) objectInputStream.readObject();
            manager.setUserSettings(defaultUserSettings);
        } catch (Exception ignored) {
            System.err.println("INTERN ERROR: THERE ARE NO DEFAULT SETTINGS");
            System.exit(1);
        }

        // .substring(6) removes "file:/" generated from maven, because this does not work with java file class
        File source = new File(getClass().getResource("/gui/settings/default_settings").toString().substring(6));
        System.out.println(source);
        File destination = new File("userdata/" + defaultUserSettings.getSettingsName());
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
