package org.example.pubstones.game.boardpieces;

import java.util.Collections;
import java.util.LinkedList;

import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;

public class StoneLine {
    private final int STANDARD_LENGTH = 7;
    
    private int maxLength = STANDARD_LENGTH;
    private LinkedList<Stone> stones;
    
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
        this.stones = new LinkedList<Stone>();
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
    public LinkedList<Stone> getStones() {
        return this.stones;
    }
    
    /**
     * The stone at the given index in this stone line
     * @param index
     * @return
     */
    public Stone getStone(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.stones.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.stones.get(index);
    }
    
    public int getIndex(Stone stone) throws StoneNotFoundException {
        if (!this.stones.contains(stone)) {
            throw new StoneNotFoundException();
        }
        return this.stones.indexOf(stone);
    }
    
    /**
     * Places a given stone on the line at the given index
     * @param stone
     * @param index
     * @throws StoneNotFoundException
     * @throws StoneAlreadyContainedException
     */
    public void placeStone(Stone stone, int index) throws StoneLineFullException, StoneAlreadyContainedException {
        if (!this.stones.contains(stone)) {
            throw new StoneAlreadyContainedException();
        }
        if (this.stones.size() >= this.maxLength) {
            throw new StoneLineFullException();
        }
        this.stones.add(index, stone);
        stone.placeOnBoard();
    }
    
    /**
     * Swaps the stones at given indexes
     * @param stone1
     * @param stone2
     */
    public void swapStones(Stone stone1, Stone stone2) throws StoneNotFoundException, StonesEqualException {
        if (!this.stones.contains(stone1)) {
            throw new StoneNotFoundException();
        }
        if (!this.stones.contains(stone1)) {
            throw new StoneNotFoundException();
        }
        if (stone1.equals(stone2)) {
            throw new StonesEqualException();
        }
        Collections.swap(this.stones, this.stones.indexOf(stone1), this.stones.indexOf(stone2));
    }
    
    /**
     * Turns the stone at the given index
     * @param index
     * @throws StoneNotFoundException
     */
    public void turnStone(Stone stone) throws StoneNotFoundException {
        if (!this.stones.contains(stone)) {
            throw new StoneNotFoundException();
        }
        stone.turn();
    }
    
}
