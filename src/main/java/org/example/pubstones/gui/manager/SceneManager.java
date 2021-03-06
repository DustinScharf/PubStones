package org.example.pubstones.gui.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.gui.util.ControlledScene;
import org.example.pubstones.util.datatype.HistoryList;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages all the scenes of the app
 */
public class SceneManager {
    private Manager manager;

    private final Stage stage;

    private final Map<String, ControlledScene> scenes;

    private final HistoryList<ControlledScene> sceneHistoryList;

    public SceneManager(Manager manager, Stage stage) {
        if (manager == null || stage == null) {
            throw new IllegalArgumentException("Manager manager or Stage stage can not be null");
        }

        this.manager = manager;

        this.stage = stage;
        this.scenes = new HashMap<>();
        this.sceneHistoryList = new HistoryList<>();
    }

    public Stage getStage() {
        return stage;
    }

    public void switchScene(String fxmlFilePath) {
        ControlledScene controlledScene = this.scenes.computeIfAbsent(fxmlFilePath, fxmlFilePathTemp -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePathTemp));
            try {
                Pane pane = loader.load();
                BaseController controller = loader.getController();
                controller.setManager(this.manager);
                return new ControlledScene(pane, controller);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        });
        this.sceneHistoryList.append(controlledScene);
        this.stage.setScene(controlledScene);
        controlledScene.getBaseController().init();
    }

    public void switchToNextScene() throws OutOfTimelineException {
        ControlledScene nextScene = this.sceneHistoryList.goNext();
        nextScene.getBaseController().init();
        this.stage.setScene(nextScene);
    }

    public void switchToPreviousScene() throws OutOfTimelineException {
        ControlledScene previousScene = this.sceneHistoryList.goPrevious();
        previousScene.getBaseController().init();
        this.stage.setScene(previousScene);
    }
}
