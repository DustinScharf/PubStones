package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;

public class PlaceMove extends GameMove {

    private Stone stone;
    private int targetIndex;
    
    /**
     * Creates a new place move with the given symbol, target index and move number
     * @param symbol
     * @param index
     * @param number
     */
    public PlaceMove(Stone stone, int index, int number) {
        super(MoveKind.Place, number);
        this.stone = stone;
        this.targetIndex = index;
    }
    
    @Override
    public void applyMove(GameField gameField) throws StoneLineFullException, StoneNotFoundException {
        gameField.tryPlaceStone(this.stone, this.targetIndex);
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
    public int getTargetIndex() {
        return this.targetIndex;
    }
    
}
