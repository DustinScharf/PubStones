package org.example.pubstones.game.boardpieces;

import java.util.ArrayList;

public class Stone {
    
    private Symbol symbol;
    private boolean turned = false;
    private boolean onBoard = false;
    
    /**
     * Creates a new stone with the given symbol
     * @param symbol the stone's symbol
     */
    public Stone(Symbol symbol) {
        this.setSymbol(symbol);
    }
    
    /**
     * This stone's symbol
     * @return 
     */
    public Symbol getSymbol() {
        return this.symbol;
    }
    
    /**
     * Sets a new symbol
     * @param symbol new symbol
     */
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    
    /**
     * This stone's turn state
     * @return
     */
    public boolean isTurned() {
        return this.turned;
    }
    
    /**
     * Sets a new turn state
     * @param turned
     */
    public void setTurned(boolean turned) {
        this.turned = turned;
    }
    
    /**
     * Turns this stone
     */
    public void turn() {
        this.turned = !this.turned;
    }
    
    /**
     * This stone's on board state
     * @return
     */
    public boolean isOnBoard() {
        return this.onBoard;
    }
    
    /**
     * Places this stone on the board
     */
    public void placeOnBoard() {
        this.onBoard = true;
    }
    
    /**
     * Creates a list of all stones
     * @return
     */
    public static ArrayList<Stone> getAll() {
        ArrayList<Stone> stones = new ArrayList<Stone>();
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < symbols.length; i++) {
            stones.add(new Stone(symbols[i]));
        }
        return stones;
    }
    
    /**
     * Creates a new stone with a given symbol index
     * @param index
     * @return
     */
    public static Stone getNew(int indexSymbol){
        return new Stone(Symbol.values()[indexSymbol]);
    }

    @Override
    public String toString() {
        return symbol + " : " + (this.turned ? "IN-visible" : "visible");
    }

    private class StoneOffBoardException extends Exception {
        public StoneOffBoardException(Stone stone) {
            super("Stone off the board: " + stone.toString());
        }
    }
    
}
