package org.example.pubstones.game.boardpieces;

public class GameField {
    
    private StoneLine stoneLine;
    private StonePile stonePile;
    
    public GameField() {
        
    }
    
    public boolean tryPlaceStone(Symbol symbol, int index) {
        if (!this.stonePile.contains(symbol)) {
            return false;
        }
        if (this.stoneLine.isFull()) {
            return false;
        }
        return stoneLine.placeStone(stonePile.takeStone(symbol), index);
    }
    
    public boolean trySwapStones(int index1, int index2) {
        return stoneLine.swapStones(index1, index2);
    }
    
}
