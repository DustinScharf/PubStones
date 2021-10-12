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
                            "No move selected...",
                            "You need to select your move type first in the bottom of the window."
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
            System.out.println(gameController.getCurrentlyBuildingGameMove().getMoveKind().getName());
            Alerter.buildInfoAlert(
                            "This move needs no stone...",
                            "You need to select a " + gameController.neededComponentsForCurrentMoveKind()
                    )
                    .showAndWait();
        }
    }

    public void symbolLineButtonClicked(GameController gameController, Symbol symbol) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move selected...",
                            "You need to select your move type first in the bottom of the window."
                    )
                    .showAndWait();
            return;
        }

        GameMove currentlyBuildingGameMove = gameController.getCurrentlyBuildingGameMove();
        try {
            currentlyBuildingGameMove.symbol(symbol);
        } catch (IllegalMoveArgumentException e) {
            Alerter.buildInfoAlert(
                            "This move needs no symbol...",
                            "You need to select a " + gameController.neededComponentsForCurrentMoveKind()
                    )
                    .showAndWait();
        }
    }

    public void stonePileButtonClicked(GameController gameController, Stone stone) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move selected...",
                            "You need to select your move type first in the bottom of the window."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().stone(stone);
        } catch (IllegalMoveArgumentException e) {
            Alerter.buildInfoAlert(
                            "This move needs no stone...",
                            "You need to select a " + gameController.neededComponentsForCurrentMoveKind()
                    )
                    .showAndWait();
        }
    }

    public void playerActionButtonClicked(GameController gameController, MoveKind moveKind) {
        gameController.setCurrentlyBuildingGameMove(GameMove.getMove(moveKind));
        gameController.getCurrentlyBuildingGameMove().sender(gameController.getGameHandler().getCurrentPlayer());
    }

    public void playerScoreButtonClicked(GameController gameController, GamePlayer gamePlayer) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            Alerter.buildInfoAlert(
                            "No move selected...",
                            "You need to select your move type first in the bottom of the window."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().player(gamePlayer);
        } catch (IllegalMoveArgumentException e) {
            Alerter.buildInfoAlert(
                            "This move needs no target player...",
                            "You need to select a " + gameController.neededComponentsForCurrentMoveKind()
                    )
                    .showAndWait();
        }
    }

    public void fireButtonClicked(GameController gameController, ActionEvent actionEvent) {
        if (gameController.getCurrentlyBuildingGameMove() == null) {
            new Tada((Button) actionEvent.getSource()).play();
            Alerter.buildInfoAlert(
                            "No move selected...",
                            "You need to select your move type first in the bottom of the window."
                    )
                    .showAndWait();
            return;
        }

        try {
            gameController.getCurrentlyBuildingGameMove().sender(gameController.getGameHandler().getCurrentPlayer());
            gameController.getGameHandler().receiveGameMove(gameController.getCurrentlyBuildingGameMove());

            gameController.setCurrentlyBuildingGameMove(null);
            new Bounce((Button) actionEvent.getSource()).play();
            gameController.updateWholeGuiToCurrentGameState();
        } catch (StoneLineFullException | StoneNotFoundException | StonesEqualException |
                StoneAlreadyContainedException | IllegalArgumentException | MissingMoveArgumentException e) {
            new Tada((Button) actionEvent.getSource()).play();

            Alerter.buildInfoAlert(
                            "Move not finished...",
                            "You need to select a " + gameController.neededComponentsForCurrentMoveKind() +
                                    "\nAfterwards you can finish the move"
                    )
                    .showAndWait();
        }
    }
}
