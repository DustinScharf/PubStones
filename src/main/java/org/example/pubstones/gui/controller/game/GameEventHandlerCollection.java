package org.example.pubstones.gui.controller.game;

import animatefx.animation.Bounce;
import animatefx.animation.Tada;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.game.gamehandling.exceptions.MissingMoveArgumentException;
import org.example.pubstones.gui.util.Alerter;

public class GameEventHandlerCollection {
    public void stoneLineButtonClicked(GameController gameController, Stone stone, MouseEvent mouseEvent) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move set...",
                            "Select a move first."
                    )
                    .showAndWait();
            return;
        }

        Button clickedButton = (Button) mouseEvent.getSource();

        boolean clickedLeft = mouseEvent.getX() <= (clickedButton.getWidth() / 2);
        gameController.getCurrentlyBuildingGameMove().left(clickedLeft);

        try {
            gameController.getCurrentlyBuildingGameMove().stone(stone);
        } catch (IllegalMoveArgumentException e) {
            Alerter.buildInfoAlert(
                            "No stone needed...",
                            "This move does not require a stone.\n" +
                                    "You can simply close this window and choose the necessary components."
                    )
                    .showAndWait();
        }
    }

    public void symbolLineButtonClicked(GameController gameController, Symbol symbol) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move set...",
                            "Select a move first."
                    )
                    .showAndWait();
            return;
        }

        GameMove currentlyBuildingGameMove = gameController.getCurrentlyBuildingGameMove();
        try {
            currentlyBuildingGameMove.symbol(symbol);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    public void stonePileButtonClicked(GameController gameController, Stone stone) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move set...",
                            "Select a move first."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().stone(stone);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    public void playerActionButtonClicked(GameController gameController, MoveKind moveKind) {
        gameController.setCurrentlyBuildingGameMove(GameMove.getMove(moveKind));
        gameController.getCurrentlyBuildingGameMove().sender(gameController.getGameHandler().getCurrentPlayer());
    }

    public void playerScoreButtonClicked(GameController gameController, GamePlayer gamePlayer) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move set...",
                            "Select a move first."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().player(gamePlayer);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    public void fireButtonClicked(GameController gameController, ActionEvent actionEvent) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move set...",
                            "Select a move first."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().sender(gameController.getGameHandler().getCurrentPlayer());
            gameController.getGameHandler().receiveGameMove(gameController.getCurrentlyBuildingGameMove());

            gameController.setCurrentlyBuildingGameMove(null);
            new Bounce((Button) actionEvent.getSource()).play();
        } catch (StoneLineFullException | StoneNotFoundException | StonesEqualException |
                StoneAlreadyContainedException | IllegalArgumentException | MissingMoveArgumentException e) {
            e.printStackTrace(); // TODO
            new Tada((Button) actionEvent.getSource()).play();
        }

        gameController.updateWholeGuiToCurrentGameState();
    }
}
