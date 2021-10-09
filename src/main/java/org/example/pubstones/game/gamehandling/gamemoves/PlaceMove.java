package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class PlaceMove extends GameMove {
    private static int[] allowedGamePlayerMoveStates = new int[] { 0, 1, -1, -1, -1 };
    
    private Stone stone = null;
    private Integer targetIndex = null;
    
    private boolean indexSet = false;
    
    public PlaceMove() {
        super(MoveKind.Place);
    }
    
    /**
     * Creates a new place move with the given symbol, target index and move number
     * @param symbol
     * @param index
     * @param number
     */
    public PlaceMove(Stone stone, int index) {
        super(MoveKind.Place);
        this.stone = stone;
        this.targetIndex = index;
    }
    
    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StoneAlreadyContainedException {
        gameHandler.getCurrentState().tryPlaceStone(this.stone, this.targetIndex);
        this.disableFirstPlayer();
    }
    
    /**
     * This move's stone
     * @return
     */
    public Stone getStone() {
        return this.stone;
    }
    
    /**
     * This move's target index
     */
    public Integer getTargetIndex() {
        return this.targetIndex;
    }

    public static int[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
    @Override
    public boolean isInitialized() {
        if (this.stone == null) {
            return false;
        }
        System.out.println("IndexSet: " + this.indexSet);
        if (!this.indexSet) {
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
    public GameMove index(int index) throws IllegalMoveArgumentException {
        this.targetIndex = index;
        this.indexSet = true;
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
