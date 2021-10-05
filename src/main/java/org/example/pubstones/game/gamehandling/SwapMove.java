package org.example.pubstones.game.gamehandling;

public class SwapMove extends GameMove {

    private int index1;
    private int index2;
    
    /**
     * Creates a new swap move with the given target indexes and move number
     * @param index1
     * @param index2
     * @param number
     */
    public SwapMove(int index1, int index2, int number) {
        super(number);
        this.index1 = index1;
        this.index2 = index2;
    }
    
    /**
     * This move's index1
     * @return
     */
    public int getIndex1() {
        return this.index1;
    }
    
    /**
     * This move's index2
     * @return
     */
    public int getIndex2() {
        return this.index2;
    }
    
}
