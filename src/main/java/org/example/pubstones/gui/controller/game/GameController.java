package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.game.gamehandling.exceptions.MissingMoveArgumentException;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

public class GameController extends BaseController {
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

    private GameEventHandlerCollection gameEventHandlerCollection;

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public GameMove getCurrentlyBuildingGameMove() {
        return currentlyBuildingGameMove;
    }

    public void setCurrentlyBuildingGameMove(GameMove currentlyBuildingGameMove) {
        this.currentlyBuildingGameMove = currentlyBuildingGameMove;
    }

    @Override
    public void init() {
        this.initNewGame();

        super.getManager().getMusicManager().setMusic("/music/game/1.mp3");
        super.getManager().getMusicManager().playMusic();
    }

    private void initNewGame() {
        this.gameHandler = new GameHandler();
        this.gameHandler.addPlayer(new GamePlayer("Player 1"));
        this.gameHandler.addPlayer(new GamePlayer("Player 2"));

        this.gameEventHandlerCollection = new GameEventHandlerCollection();

        //
        // Creates the buttons for the symbols
        //
        this.symbolsHBox.getChildren().clear();
        int symbolsSize = Symbol.values().length;
        for (int i = 0; i < symbolsSize; ++i) {
            Symbol currentSymbol = Symbol.values()[i];
            Button symbolButton = new Button(
                    "" + currentSymbol
            );
            symbolButton.setOnAction(clickEvent ->
                    this.gameEventHandlerCollection.symbolLineButtonClicked(this, currentSymbol));
            this.symbolsHBox.getChildren().add(symbolButton);
        }

        this.updateGuiToCurrentGameState();
    }

    public void updateGuiToCurrentGameState() {
        //
        // Update the stone line display
        //
        this.stoneLineHBox.getChildren().clear();
        int stoneLineSize = this.gameHandler.getCurrentState().getStoneLine().getStones().size();
        for (int i = 0; i < stoneLineSize; ++i) {
            Stone currentStone = this.gameHandler.getCurrentState().getStoneLine().getStones().get(i);
            Button stoneLineButton = new Button("" + currentStone);

            stoneLineButton.setOnMouseClicked(event ->
                    this.gameEventHandlerCollection.stoneLineButtonClicked(this, currentStone, event));

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
            stonePileButton.setOnAction(clickEvent ->
                    this.gameEventHandlerCollection.stonePileButtonClicked(this, currentStone));
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
                    this.gameEventHandlerCollection.playerActionButtonClicked(this, currentMoveKind));
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

    @FXML
    public void fireButtonClicked(ActionEvent actionEvent) {
        this.gameEventHandlerCollection.fireButtonClicked(this);
    }

    @FXML
    public void backButtonClicked(ActionEvent actionEvent) {
        try {
            super.getManager().getMusicManager().stopMusic();
            super.getManager().getSceneManager().switchToPreviousScene();
        } catch (OutOfTimelineException e) {
            e.printStackTrace(); // TODO
        }
    }
}
