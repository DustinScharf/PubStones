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
    
    /**
     * This stone line's stones
     * @return
     */
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
     * @return true, if successfull
     */
    public boolean placeStone(Stone stone, int index) {
        if (this.stones.size() >= this.maxLength) {
            return false;
        }
        this.addStone(stone);
        stone.placeOnBoard(index);
        for (Stone s : stones) {
            if (s.getIndex() >= index) {
                s.moveRight();
            }
        }
        return true;
    }
    
    /**
     * Swaps the stones at given indexes
     * @param index1
     * @param index2
     * @return true, if successfull
     */
    public boolean swapStones(int index1, int index2) {
        if (index1 < 0 && index1 >= this.stones.size()) {
            return false;
        }
        if (index2 < 0 && index2 >= this.stones.size()) {
            return false;
        }
        if (index1 == index2) {
            return false;
        }
        Stone.swap(this.getStone(index1), this.getStone(index2));
        return true;
    }
    
    /**
     * Turns the stone at the given index
     * @param index
     */
    public void turnStone(int index) {
        this.getStone(index).turn();
    }
    
    /**
     * This symbol's stone in the line
     * @param symbol
     * @return
     */
    public Stone getStone(Symbol symbol) {
        for (int i = 0; i < this.stones.size(); i++) {
            if (this.stones.get(i).getSymbol().equals(symbol)) {
                return this.stones.get(i);
            }
        }
        // TODO Error handling
        return null;
    }
    
    /**
     * This symbol's index in the line
     * @param symbol
     * @return
     */
    public int getIndex(Symbol symbol) {
        Stone stone = this.getStone(symbol);
        if (stone != null) {
            return stone.getIndex();
        }
        return -1;
    }
    
    /**
     * Removes the stone at the given symbol
     * @param symbol
     * @return
     */
    public boolean removeStone(Symbol symbol) {
        Stone stone = this.getStone(symbol);
        if (stone != null) {
            this.stones.remove(stone);
            return true;
        }
        return false;
    }
    
}
