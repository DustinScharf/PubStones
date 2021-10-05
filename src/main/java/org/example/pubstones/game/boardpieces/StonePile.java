package org.example.pubstones.game.boardpieces;

import java.util.ArrayList;

public class StonePile {
    private final int STANDARD_STARTSTONES = 7;
    
    private int startStones = STANDARD_STARTSTONES;
    private ArrayList<Stone> stones;
    
    /**
     * Creates a new stone pile with given amount of start stones
     * @param startStones
     */
    public StonePile(int startStones) {
        this.startStones = startStones;
        this.initializeStones();
    }
    
    /**
     * Creates a new stone pile with standard amount of start stones
     */
    public StonePile() {
        this.initializeStones();
    }
    
    /**
     * Initializes the pile of stones
     */
    private void initializeStones() {
        this.stones = Stone.getAll();
    }
    
    /**
     * Takes a stone from this pile
     * @param symbol
     * @return
     */
    public Stone takeStone(Stone stone) {
        this.stones.remove(stone);
        return stone;
    }
    
    /**
     * Checks whether the stone with the given symbol is still on the pile
     * @param symbol
     * @return
     */
    public boolean contains(Stone stone) {
        if (this.isEmpty()) {
            return false;
        }
        return this.stones.contains(stone);
    }
    
    /**
     * Checks whether this pile is empty
     * @return
     */
    public boolean isEmpty() {
        return this.stones.isEmpty();
    }
    
    /**
     * A list of all stones in this pile
     * @return
     */
    public ArrayList<Stone> getStones() {
        return this.stones;
    }
    
}
