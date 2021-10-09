package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class SwapMove extends GameMove {
    private static int[] allowedGamePlayerMoveStates = new int[] { -1, 1, -1, -1, -1 };

    private Stone stone1;
    private Stone stone2;
    
    private boolean firstStone = true;
    
    public SwapMove() {
        super(MoveKind.Swap);
    }
    
    /**
     * Creates a new swap move with the given target indexes and move number
     * @param index1
     * @param index2
     * @param number
     */
    public SwapMove(Stone stone1, Stone stone2) {
        super(MoveKind.Swap);
        this.stone1 = stone1;
        this.stone2 = stone2;
    }
    
    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        gameHandler.getCurrentState().trySwapStones(this.stone1, this.stone2);
        this.disableFirstPlayer();
    }
    
    /**
     * This move's stone1
     * @return
     */
    public Stone getStone1() {
        return this.stone1;
    }
    
    /**
     * This move's stone2
     * @return
     */
    public Stone getStone2() {
        return this.stone2;
    }

    public static int[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
    @Override
    public boolean isInitialized() {
        if (this.stone1 == null) {
            return false;
        }
        if (this.stone2 == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public GameMove stone(Stone stone) throws IllegalMoveArgumentException {
        if (firstStone) {
            this.stone1 = stone;
        } else {
            this.stone2 = stone;
        }
        firstStone = !firstStone;
        return this;
    }

    @Override
    public GameMove left(boolean left) {
        return this;
    }

    @Override
    public GameMove player(GamePlayer gamePlayer) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Integer.class);
    }
    
    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Symbol.class);
    }
}
