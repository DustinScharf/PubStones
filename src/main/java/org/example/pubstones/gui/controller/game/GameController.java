package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.StonePile;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {
    @FXML
    private HBox stoneLineHBox;

    @FXML
    private Button placeLeftButton;

    @FXML
    private Button placeRightButton;

    @FXML
    private HBox symbolsHBox;

    @FXML
    private VBox scoresVBox;

    @FXML
    private HBox stonePileHBox;

    @FXML
    private HBox playerActionsHBox;

    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Label winnerLabel;

    private GameHandler gameHandler;

    private GameMove currentlyBuildingGameMove;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initNewGame();
    }

    private void initNewGame() {
        this.gameHandler = new GameHandler();
        this.gameHandler.addPlayer(new GamePlayer("Player 1"));
        this.gameHandler.addPlayer(new GamePlayer("Player 2"));

        //
        // Creates the buttons for the symbols
        //
        int symbolsSize = Symbol.values().length;
        for (int i = 0; i < symbolsSize; ++i) {
            Button symbolButton = new Button(
                    "" + Symbol.values()[i]
            );
            this.symbolsHBox.getChildren().add(symbolButton);
        }

        // TODO extract to game logic
        // Sets a random stone into the stone line
        try {
            int stonePileSize = this.gameHandler.getCurrentState().getStonePile().getStones().size();
            StonePile stonePile = this.gameHandler.getCurrentState().getStonePile();
            Stone stoneToPlace = stonePile.getStones().get(new Random().nextInt(stonePileSize));

            GameMove gameMove = GameMove.getMove(MoveKind.Place).index(0).stone(stoneToPlace);
            this.gameHandler.receiveGameMove(gameMove);
        } catch (IllegalMoveArgumentException |
                StonesEqualException |
                StoneLineFullException |
                StoneNotFoundException e) {
            e.printStackTrace();
        }

        this.updateGuiToCurrentGameState();
    }

    private void updateGuiToCurrentGameState() {
        //
        // Update the stone line display
        //
        this.stoneLineHBox.getChildren().clear();
        int stoneLineSize = this.gameHandler.getCurrentState().getStoneLine().getStones().size();
        for (int i = 0; i < stoneLineSize; ++i) {
            Button stoneLineButton = new Button(
                    "" + this.gameHandler.getCurrentState().getStoneLine().getStones().get(i)
            );
            this.stoneLineHBox.getChildren().add(stoneLineButton);
        }

        //
        // Update the score display of all players
        //
        this.scoresVBox.getChildren().clear();
        int playerCount = this.gameHandler.getPlayers().size();
        for (int i = 0; i < playerCount; ++i) {
            GamePlayer currentPlayer = this.gameHandler.getPlayers().get(i);
            Label playerScoreLabel = new Label(currentPlayer.getName() + " : " + currentPlayer.getScore());
            this.scoresVBox.getChildren().add(playerScoreLabel);
        }

        //
        // Updates the stone pile display
        //
        this.stonePileHBox.getChildren().clear();
        int stonePileSize = this.gameHandler.getCurrentState().getStonePile().getStones().size();
        for (int i = 0; i < stonePileSize; ++i) {
            Stone currentStone = this.gameHandler.getCurrentState().getStonePile().getStones().get(i);
            Button stonePileButton = new Button(
                    "" + currentStone
            );
            stonePileButton.setOnAction(clickEvent -> {
                try {
                    this.currentlyBuildingGameMove.stone(currentStone);
                } catch (IllegalMoveArgumentException e) {
                    e.printStackTrace(); // TODO
                }
            });
            this.stonePileHBox.getChildren().add(stonePileButton);
        }

        //
        // Updates the possible player actions display
        //
        this.playerActionsHBox.getChildren().clear();
        int playerActionsCount = this.gameHandler.getCurrentPlayer().getPossibleMoves().length;
        for (int i = 0; i < playerActionsCount; ++i) {
            MoveKind currentMoveKind = this.gameHandler.getCurrentPlayer().getPossibleMoves()[i];
            Button playerActionButton = new Button("" + currentMoveKind);
            playerActionButton.setOnAction(clickEvent ->
                    this.currentlyBuildingGameMove = GameMove.getMove(currentMoveKind));
            this.playerActionsHBox.getChildren().add(playerActionButton);
        }

        //
        // Updates the current player display
        //
        this.currentPlayerLabel.setText("Current Player: " + this.gameHandler.getCurrentPlayer().getName());

        //
        // Updates the winner display
        //
        String winnerInfo = this.gameHandler.isGameOver() ? this.gameHandler.getLeadingPlayer().getName() : "none";
        this.winnerLabel.setText("Winner: " + winnerInfo);
    }

    // TODO
    @FXML
    public void placeLeftButtonClicked(ActionEvent actionEvent) {
        try {
            this.currentlyBuildingGameMove.index(0);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    @FXML
    public void placeRightButtonClicked(ActionEvent actionEvent) {
        try {
            this.currentlyBuildingGameMove.index(this.gameHandler.getCurrentState().getStoneLine().getStones().size());
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }

        try {
            this.gameHandler.receiveGameMove(this.currentlyBuildingGameMove);
        } catch (StoneLineFullException | StoneNotFoundException | StonesEqualException e) {
            e.printStackTrace(); // TODO
        }

        this.updateGuiToCurrentGameState();
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
