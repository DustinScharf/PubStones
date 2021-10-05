package org.example.pubstones.game.gamehandling;

import org.example.pubstones.game.boardpieces.Symbol;

public class PlaceMove extends GameMove {

    private Symbol symbol;
    private int targetIndex;
    
    /**
     * Creates a new place move with the given symbol, target index and move number
     * @param symbol
     * @param index
     * @param number
     */
    public PlaceMove(Symbol symbol, int index, int number) {
        super(number);
        this.symbol = symbol;
        this.targetIndex = index;
    }
    
    /**
     * This move's symbol
     * @return
     */
    public Symbol getSymbol() {
        return this.symbol;
    }
    
    /**
     * This move's target index
     */
    public int getTargetIndex() {
        return this.targetIndex;
    }
    
}
