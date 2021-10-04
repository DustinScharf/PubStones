package org.example.pubstones.gui.controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.datatyp.historylist.OutOfTimelineException;

public class StartMenuController extends BaseController {
    @FXML
    public void playButtonClicked(ActionEvent actionEvent) {
        super.sceneManager.switchScene("/fxml/menu/Game.fxml");
    }

    @FXML
    public void exitButtonClicked(ActionEvent actionEvent) {
        super.sceneManager.getStage().close();
    }
}
