package org.example.pubstones.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.datatype.historylist.HistoryList;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private final Stage stage;

    private final Map<String, Scene> scenes;

    private final HistoryList<Scene> sceneHistoryList;

    public SceneManager(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage stage can not be null");
        }

        this.stage = stage;
        this.scenes = new HashMap<>();
        this.sceneHistoryList = new HistoryList<>();
    }

    public Stage getStage() {
        return stage;
    }

    public void switchScene(String fxmlFilePath) {
        Scene scene = this.scenes.computeIfAbsent(fxmlFilePath, fxmlFilePathTemp -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePathTemp));
            try {
                Pane pane = loader.load();
                BaseController controller = loader.getController();
                controller.setSceneManager(this);
                return new Scene(pane);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        });
        this.sceneHistoryList.append(scene);
        this.stage.setScene(scene);
    }

    public void switchToNextScene() throws OutOfTimelineException {
        this.stage.setScene(this.sceneHistoryList.goNext());
    }

    public void switchToPreviousScene() throws OutOfTimelineException {
        this.stage.setScene(this.sceneHistoryList.goPrevious());
    }
}
