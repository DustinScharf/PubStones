package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {
    private GameHandler gameHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameHandler = new GameHandler();
        this.gameHandler.addPlayer(new GamePlayer("Player 1"));
        this.gameHandler.addPlayer(new GamePlayer("Player 2"));
    }

    @FXML
    public void backButtonClicked(ActionEvent actionEvent) {
        try {
            super.sceneManager.switchToPreviousScene();
        } catch (OutOfTimelineException e) {
            e.printStackTrace();
        }
    }
}
