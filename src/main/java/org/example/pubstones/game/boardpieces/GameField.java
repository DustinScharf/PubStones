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
    
}
