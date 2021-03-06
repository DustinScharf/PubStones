package org.example.pubstones.gui.controller.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.gui.controller.BaseController;
import org.example.pubstones.util.exception.OutOfTimelineException;

/**
 * Controller for the Game Gui (it connects game logic with gui)
 */
public class GameController extends BaseController {
    @FXML
    private HBox stoneLineHBox;

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
        super.getManager().getMusicManager().setMusic("/music/game/1.mp3");
        super.getManager().getMusicManager().playMusic();

        this.initNewGame();
    }

    private void initNewGame() {
        this.gameHandler = new GameHandler();
        this.gameHandler.addPlayer(new GamePlayer(super.getManager().getUserSettings().getName()));
        this.gameHandler.addPlayer(new GamePlayer("Second Player"));

        this.gameEventHandlerCollection = new GameEventHandlerCollection();

        this.createSymbolDisplay();

        this.updateWholeGuiToCurrentGameState();
    }

    private void createSymbolDisplay() {
        this.symbolsHBox.getChildren().clear();
        int symbolsSize = Symbol.values().length;
        for (int i = 0; i < symbolsSize; ++i) {
            Symbol currentSymbol = Symbol.values()[i];
            Button symbolButton = new Button("" + currentSymbol);
            symbolButton.setOnAction(clickEvent ->
                    this.gameEventHandlerCollection.symbolLineButtonClicked(this, currentSymbol));
            this.symbolsHBox.getChildren().add(symbolButton);
        }
    }

    private void updateWholeStoneLineDisplay() {
        this.stoneLineHBox.getChildren().clear();
        int stoneLineSize = this.gameHandler.getCurrentState().getStoneLine().getStones().size();
        for (int i = 0; i < stoneLineSize; ++i) {
            Stone currentStone = this.gameHandler.getCurrentState().getStoneLine().getStones().get(i);
            Button stoneLineButton = new Button("" + currentStone);

            stoneLineButton.setOnMouseClicked(event ->
                    this.gameEventHandlerCollection.stoneLineButtonClicked(this, currentStone, event));

            this.stoneLineHBox.getChildren().add(stoneLineButton);
        }
    }

    private void updateWholeScoreDisplay() {
        this.scoresVBox.getChildren().clear();
        int playerCount = this.gameHandler.getPlayers().size();
        for (int i = 0; i < playerCount; ++i) {
            GamePlayer currentPlayer = this.gameHandler.getPlayers().get(i);
            Button playerScoreButton = new Button(currentPlayer.getName() + " : " + currentPlayer.getScore());

            playerScoreButton.setOnAction(actionEvent ->
                    this.gameEventHandlerCollection.playerScoreButtonClicked(this, currentPlayer));

            this.scoresVBox.getChildren().add(playerScoreButton);
        }
    }

    private void updateWholeStonePileDisplay() {
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
    }

    public void updateWholePlayerActionDisplay() {
        this.playerActionsHBox.getChildren().clear();
        int playerActionsCount = this.gameHandler.getCurrentPlayer().getPossibleMoves().length;
        for (int i = 0; i < playerActionsCount; ++i) {
            MoveKind currentMoveKind = this.gameHandler.getCurrentPlayer().getPossibleMoves()[i];
            Button playerActionButton = new Button("" + currentMoveKind);
            playerActionButton.setOnAction(clickEvent ->
                    this.gameEventHandlerCollection.playerActionButtonClicked(
                            this,
                            currentMoveKind,
                            clickEvent
                    ));
            this.playerActionsHBox.getChildren().add(playerActionButton);
        }
    }

    private void updateWholeCurrentPlayerDisplay() {
        this.currentPlayerLabel.setText("Current Player: " + this.gameHandler.getCurrentPlayer().getName());
    }

    private void updateWholeWinnerDisplay() {
        String winner = this.gameHandler.getLeadingPlayer().getName();
        this.winnerLabel.setText(this.gameHandler.isGameOver() ? "Winner: " + winner : "Game running...");
    }

    public void updateWholeGuiToCurrentGameState() {
        this.updateWholeStoneLineDisplay();
        this.updateWholeScoreDisplay();
        this.updateWholeStonePileDisplay();
        this.updateWholePlayerActionDisplay();
        this.updateWholeCurrentPlayerDisplay();
        this.updateWholeWinnerDisplay();
    }

    @FXML
    public void fireButtonClicked(ActionEvent actionEvent) {
        this.gameEventHandlerCollection.fireButtonClicked(this, actionEvent);
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

    // UTIL
    public String neededComponentsForCurrentMoveKind() {
        int neededComponentsForCurrentMoveKindAmount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.currentlyBuildingGameMove.getMoveKind().containsArgumentClass(Stone.class)) {
            stringBuilder.append("Stone, ");
            ++neededComponentsForCurrentMoveKindAmount;
        }
        if (this.currentlyBuildingGameMove.getMoveKind().containsArgumentClass(Symbol.class)) {
            stringBuilder.append("Symbol, ");
            ++neededComponentsForCurrentMoveKindAmount;
        }
        if (this.currentlyBuildingGameMove.getMoveKind().containsArgumentClass(GamePlayer.class)) {
            stringBuilder.append("GamePlayer, ");
            ++neededComponentsForCurrentMoveKindAmount;
        }
        if (this.currentlyBuildingGameMove.getMoveKind().containsArgumentClass(boolean.class)) {
            stringBuilder.append("Place Side, ");
            ++neededComponentsForCurrentMoveKindAmount;
        }

        // delete "," at end
        if (neededComponentsForCurrentMoveKindAmount >= 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }
}
