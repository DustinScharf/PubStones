package org.example.pubstones.gui.launcher;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.pubstones.gui.manager.SceneManager;

public class PubStonesApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.switchScene("/gui/fxml/menu/StartMenu.fxml");

        primaryStage.show();
    }
}
