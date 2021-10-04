package org.example.pubstones.game.boardpieces;

import java.util.ArrayList;

public class StoneLine {
    private final int STANDARD_LENGTH = 7;
    
    private int maxLength = STANDARD_LENGTH;
    private ArrayList<Stone> stones;
    
    /**
     * Creates a new stone line with given max length
     * @param maxLength
     */
    public StoneLine(int maxLength) {
        this.maxLength = maxLength;
        initializeStones();
    }
    
    /**
     * Creates a new stone line with standard max length
     */
    public StoneLine() {
        initializeStones();
    }
    
    /**
     * Initializes the stone line's array list
     */
    private void initializeStones() {
        this.stones = new ArrayList<Stone>();
    }
    
    /**
     * Checks whether this stone line is empty
     * @return
     */
    public boolean isEmpty() {
        return this.stones.isEmpty();
    }
    
    /**
     * Checks whether this stone line is full
     * @return
     */
    public boolean isFull() {
        return this.stones.size() == this.maxLength;
    }
    
    public ArrayList<Stone> getStones() {
        return this.stones;
    }
    
    /**
     * Searches the stone with the given index in this stone line
     * @param index
     * @return
     */
    private Stone getStone(int index) {
        for (int i = 0; i < stones.size(); i++) {
            if (stones.get(i).getIndex() == index) {
                return stones.get(i);
            }
        }
        // TODO Exception handling
        return null;
    }
    
    /**
     * Adds a given stone to the array list
     * @param stone
     */
    private void addStone(Stone stone) {
        this.stones.add(stone);
    }
    
    /**
     * Places a given stone on the line at the given index
     * @param stone
     * @param index
     */
    public void placeStone(Stone stone, int index) {
        if (this.stones.size() >= this.maxLength) {
            return;
        }
        this.addStone(stone);
        stone.placeOnBoard(index);
        for (Stone s : stones) {
            if (s.getIndex() >= index) {
                s.moveRight();
            }
        }
    }
    
    /**
     * Swaps the stones at given indexes
     * @param index1
     * @param index2
     */
    public void swapStones(int index1, int index2) {
        Stone.swap(this.getStone(index1), this.getStone(index2));
    }
    
    /**
     * Turns the stone at the given index
     * @param index
     */
    public void turnStone(int index) {
        this.getStone(index).turn();
    }
    
}
