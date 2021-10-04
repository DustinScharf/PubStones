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
        this.stones = new ArrayList<Stone>();
        for (int i = 0; i < startStones; i++) {
            this.stones.add(Stone.getNew(i));
        }
    }
    
    /**
     * Takes a stone from this pile
     * @param symbol
     * @return
     */
    public Stone takeStone(Stone.Symbol symbol) {
        for (int i = 0; i < stones.size(); i++) {
            if (stones.get(i).getSymbol().equals(symbol)) {
                return this.stones.remove(i);
            }
        }
        // TODO Exception handling
        return null;
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
