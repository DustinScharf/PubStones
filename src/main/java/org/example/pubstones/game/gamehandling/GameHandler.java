package org.example.pubstones.game.gamehandling;

import java.util.ArrayList;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.gamehandling.gamemoves.PlaceMove;
import org.example.pubstones.game.gamehandling.gamemoves.SwapMove;

public class GameHandler {
    
    private ArrayList<Player> players;
    private MoveHistory moveHistory;
    
    private GameField gameField;
    
    public GameField getCurrentState() {
        return this.gameField;
    }
    
    public void receiveGameMove(GameMove gameMove) {
        switch (gameMove.getMoveKind()) {
            case Place:
                gameField.tryPlaceStone(((PlaceMove) gameMove).getSymbol(), ((PlaceMove) gameMove).getTargetIndex());
            case Swap:
                gameField.trySwapStones(((SwapMove) gameMove).getIndex1(), ((SwapMove) gameMove).getIndex2());
        }
        moveHistory.add(gameMove);
    }
    
    public void undo() {
        GameMove lastMove = this.moveHistory.undo();
        switch (lastMove.getMoveKind()) {
            case Place:
                gameField.tryUndoPlace(((PlaceMove)lastMove).getSymbol());
            case Swap:
                gameField.tryUndoSwap(((SwapMove) lastMove).getIndex1(), ((SwapMove) lastMove).getIndex2());
        }
    }
    
}
