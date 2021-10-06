package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {
    @FXML
    public HBox stoneLineHBox;

    @FXML
    public HBox symbolsHBox;

    @FXML
    public VBox scoresVBox;

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

        int symbolsSize = Symbol.values().length;
        for (int i = 0; i < symbolsSize; ++i) {
            Button symbolButton = new Button(
                    "" + Symbol.values()[i]
            );
            this.symbolsHBox.getChildren().add(symbolButton);
        }

        int stonePileSize = this.gameHandler.getCurrentState().getStonePile().getStones().size();
        for (int i = 0; i < stonePileSize; ++i) {
            Button stonePileButton = new Button(
                    "" + this.gameHandler.getCurrentState().getStonePile().getStones().get(i)
            );
            this.stonePileHBox.getChildren().add(stonePileButton);
        }

        int playerCount = this.gameHandler.getPlayers().size();
        for (int i = 0; i < playerCount; ++i) {
            GamePlayer currentPlayer = this.gameHandler.getPlayers().get(i);
            Label playerScoreLabel = new Label(currentPlayer.getName() + " : " + currentPlayer.getScore());
            this.scoresVBox.getChildren().add(playerScoreLabel);
        }

        this.currentPlayerLabel.setText("Current Player: " + this.gameHandler.getCurrentPlayer().getName());

        this.winnerLabel.setText(
                "Winner: " + (this.gameHandler.isGameOver() ? this.gameHandler.getLeadingPlayer().getName() : "none")
        );

        int playerActionsCount = this.gameHandler.getCurrentPlayer().getPossibleMoves().length;
        for (int i = 0; i < playerActionsCount; ++i) {
            Button playerActionButton = new Button("" + this.gameHandler.getCurrentPlayer().getPossibleMoves()[i]);
            this.playerActionsHBox.getChildren().add(playerActionButton);
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
