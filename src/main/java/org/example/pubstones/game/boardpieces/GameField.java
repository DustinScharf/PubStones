package org.example.pubstones.game.boardpieces;

public class GameField {
    
    private StoneLine stoneLine;
    private StonePile stonePile;
    
    public GameField() {
    }
    
    /**
     * Tries to place a stone from the pile on the given index
     * @param symbol
     * @param index
     */
    public void tryPlaceStone(Stone stone, int index) {
        if (!this.stonePile.contains(stone)) {
            return;
        }
        if (this.stoneLine.isFull()) {
            return;
        }
        stoneLine.placeStone(stonePile.takeStone(stone), index);
    }
    
    /**
     * Tries to swap the given stones 
     * @param index1
     * @param index2
     */
    public void trySwapStones(Stone stone1, Stone stone2) {
        this.stoneLine.swapStones(stone1, stone2);
    }
    
    /**
     * Tries to turn the given stone
     * @param stone
     */
    public void tryTurnStones(Stone stone) {
        this.stoneLine.turnStone(stone);
    }
    
}
