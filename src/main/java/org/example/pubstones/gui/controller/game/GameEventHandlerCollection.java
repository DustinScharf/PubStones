package org.example.pubstones.gui.controller.game;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.game.gamehandling.exceptions.MissingMoveArgumentException;

public class GameEventHandlerCollection {
    public void stoneLineButtonClicked(GameController gameController, Stone stone, MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();

        boolean clickedLeft = mouseEvent.getX() <= (clickedButton.getWidth() / 2);
        gameController.getCurrentlyBuildingGameMove().left(clickedLeft);

        try {
            gameController.getCurrentlyBuildingGameMove().stone(stone);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    public void symbolLineButtonClicked(GameController gameController, Symbol symbol) {
        GameMove currentlyBuildingGameMove = gameController.getCurrentlyBuildingGameMove();
        try {
            currentlyBuildingGameMove.symbol(symbol);
        } catch (IllegalMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }
    }

    public void stonePileButtonClicked(GameController gameController, Stone stone) {
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

    public void fireButtonClicked(GameController gameController) {
        try {
            gameController.getCurrentlyBuildingGameMove().sender(gameController.getGameHandler().getCurrentPlayer());
            gameController.getGameHandler().receiveGameMove(gameController.getCurrentlyBuildingGameMove());

            gameController.setCurrentlyBuildingGameMove(null);
        } catch (StoneLineFullException | StoneNotFoundException | StonesEqualException |
                StoneAlreadyContainedException | IllegalArgumentException | MissingMoveArgumentException e) {
            e.printStackTrace(); // TODO
        }

        gameController.updateWholeGuiToCurrentGameState();
    }
}
