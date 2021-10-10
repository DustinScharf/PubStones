package org.example.pubstones.gui.controller.game;

import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

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
        // TODO
    }

    public void playerActionButtonClicked(GameController gameController, Stone stone) {
        // TODO
    }

    public void fireButtonClicked(GameController gameController, Stone stone) {
        // TODO
    }
}
