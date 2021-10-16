package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.manager.Manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PubStonesApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Manager manager = new Manager(primaryStage);

//        manager.getSceneManager().getStage().setFullScreen(true);

        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream("userdata/default_settings"))) {
            UserSettings userSettings = (UserSettings) objectInputStream.readObject();
            manager.applyUserSettings(userSettings);
        } catch (IOException ioException) {
            ioException.printStackTrace(); // TODO
        }

        manager.getSceneManager().switchScene("/gui/fxml/menu/StartMenu.fxml");

        primaryStage.show();
    }
}
