package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.pubstones.gui.UserSettings;
import org.example.pubstones.gui.manager.Manager;

public class PubStonesApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Manager manager = new Manager(primaryStage);

//        manager.getSceneManager().getStage().setFullScreen(true);

        UserSettings userSettings = new UserSettings("settings1");
        userSettings.setVolume(0.25);
        manager.applyUserSettings(userSettings);

        manager.getSceneManager().switchScene("/gui/fxml/menu/StartMenu.fxml");

        primaryStage.show();
    }
}
