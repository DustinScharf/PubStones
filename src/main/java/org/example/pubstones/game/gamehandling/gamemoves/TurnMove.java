package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class TurnMove extends GameMove {
    private static int[] allowedGamePlayerMoveStates = new int[] { 0, 1, -1, -1, -1 };

    private Stone stone;
    
    public TurnMove() {
        super(MoveKind.Turn);
    }
    
    public TurnMove(Stone stone) {
        super(MoveKind.Turn);
        this.stone = stone;
    }
    
    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException {
        gameHandler.getCurrentState().tryTurnStones(this.stone);
        this.disableFirstPlayer();
    }
    
    /**
     * This move's stone
     * @return
     */
    public Stone getStone() {
        return this.stone;
    }

    public static int[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
    @Override
    public boolean isInitialized() {
        if (this.stone == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public GameMove stone(Stone stone) throws IllegalMoveArgumentException {
        this.stone = stone;
        return this;
    }
    
    @Override
    public GameMove left(boolean left) {
        return this;
    }
    
    @Override
    public GameMove player(GamePlayer gamePlayer) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(GamePlayer.class);
    }
    
    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Symbol.class);
    }
}
