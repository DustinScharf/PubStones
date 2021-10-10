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
    
    private Stone stone1 = null;
    private Stone stone2 = null;
    private boolean left = true;
    
    private boolean firstStone = true;
    
    public PlaceMove() {
        super(MoveKind.Place);
    }
    
    /**
     * Creates a new place move with the given symbol, target index and move number
     * @param symbol
     * @param index
     * @param number
     */
    public PlaceMove(Stone stone1, Stone stone2, boolean isLeft) {
        super(MoveKind.Place);
        this.stone1 = stone1;
        this.stone2 = stone2;
    }
    
    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StoneAlreadyContainedException {
        Stone stoneToPlace;
        int targetIndex;
        if (gameHandler.getCurrentState().getStonePile().contains(stone1)) {
            stoneToPlace = stone1;
            targetIndex = gameHandler.getCurrentState().getStoneLine().getIndex(stone2);
        } else {
            stoneToPlace = stone2;
            targetIndex = gameHandler.getCurrentState().getStoneLine().getIndex(stone1);
        }
        if (!this.left) {
            targetIndex++;
        }
        gameHandler.getCurrentState().tryPlaceStone(stoneToPlace, targetIndex);
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
    public GameMove left(boolean isLeft) {
        this.left = isLeft;
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
