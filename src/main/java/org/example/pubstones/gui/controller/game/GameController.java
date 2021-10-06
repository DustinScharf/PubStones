package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {
    @FXML
    public HBox stoneLineHBox;

    @FXML
    public HBox scoresHBox;

    @FXML
    public HBox stonePileHBox;

    @FXML
    public HBox playerActionsHBox;

    @FXML
    public Label currentPlayerLabel;

    @FXML
    public Label winnerLabel;

    private GameHandler gameHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameHandler = new GameHandler();
        this.gameHandler.addPlayer(new GamePlayer("Player 1"));
        this.gameHandler.addPlayer(new GamePlayer("Player 2"));

        int stonePileSize = this.gameHandler.getCurrentState().getStonePile().getStones().size();
        for (int i = 0; i < stonePileSize; ++i) {
            this.stonePileHBox.getChildren().add(new Button("b" + i));
        }
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
