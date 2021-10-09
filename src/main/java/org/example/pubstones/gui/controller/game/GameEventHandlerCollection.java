package org.example.pubstones.gui.controller.game;

import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class GameEventHandlerCollection {
    public void symbolClicked(GameController gameController, Symbol symbol) {
        GameMove currentlyBuildingGameMove = gameController.getCurrentlyBuildingGameMove();
        if (currentlyBuildingGameMove.isMoveKind(MoveKind.Challenge) ||
                currentlyBuildingGameMove.isMoveKind(MoveKind.Boast)) {
            try {
                currentlyBuildingGameMove.symbol(symbol);
            } catch (IllegalMoveArgumentException e) {
                e.printStackTrace(); // TODO
            }
        }
    }
}
