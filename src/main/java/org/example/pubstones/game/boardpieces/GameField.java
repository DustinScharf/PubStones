package org.example.pubstones.game.boardpieces;

public class GameField {
    
    private StoneLine stoneLine;
    private StonePile stonePile;
    
    public GameField() {

    }
    
    /**
     * Tries to place a stone from the pile with the given symbol on the given index
     * @param symbol
     * @param index
     * @return true, if successfull
     */
    public boolean tryPlaceStone(Symbol symbol, int index) {
        if (!this.stonePile.contains(symbol)) {
            return false;
        }
        if (this.stoneLine.isFull()) {
            return false;
        }
        return stoneLine.placeStone(stonePile.takeStone(symbol), index);
    }
    
    /**
     * Tries to swap the stones at the given indexes 
     * @param index1
     * @param index2
     * @return true, if successfull
     */
    public boolean trySwapStones(int index1, int index2) {
        return stoneLine.swapStones(index1, index2);
    }
    
    /**
     * Tries to undo the placement of the given symbol
     * @param symbol
     * @return true, if successfull
     */
    public boolean tryUndoPlace(Symbol symbol) {
        if (this.stoneLine.removeStone(symbol)) {
            this.stonePile.reAdd(symbol);
            return true;
        }
        return false;
    }
    
    /**
     * Tries to undo the swap of the given indexes
     * @param index1
     * @param index2
     * @return true, if successfull
     */
    public boolean tryUndoSwap(int index1, int index2) {
        return this.stoneLine.swapStones(index1, index2);
    }
    
}
