package org.example.pubstones.game.boardpieces;

public class Stone {
    
    private Symbol symbol;
    private boolean turned = false;
    private boolean onBoard = false;
    
    private int index;
    
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
     * This stone's index
     * @return
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Moves this stone to the right
     */
    public void moveRight() {
        this.index++;
    }
    
    /**
     * Moves this stone to the left
     * @throws StoneOffBoardException
     */
    public void moveLeft() throws StoneOffBoardException{
        this.index--;
        if (index < 0) {
            this.index = 0;
            throw new StoneOffBoardException(this);
        }
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
    public void placeOnBoard(int index) {
        this.index = index;
        this.onBoard = true;
    }
    
    /**
     * Swaps this stone's index with the given stone's index
     * @param s target stone
     */
    public void swap(Stone s) {
        Stone.swap(this, s);
    }
    
    /**
     * Swaps the given stone's indexes
     * @param s1 stone 1
     * @param s2 stone 2
     */
    public static void swap(Stone s1, Stone s2) {
        int i = s1.index;
        s1.index = s2.index;
        s2.index = i;
    }
    
    /**
     * Creates a new stone with a given symbol index
     * @param index
     * @return
     */
    public static Stone getNew(int indexSymbol){
        switch (indexSymbol) {
                case 0:
                    return new Stone(Symbol.A);
                case 1:
                    return new Stone(Symbol.B);
                case 2:
                    return new Stone(Symbol.C);
                case 3:
                    return new Stone(Symbol.D);
                case 4:
                    return new Stone(Symbol.E);
                case 5:
                    return new Stone(Symbol.F);
                case 6:
                    return new Stone(Symbol.G);
                default:
                    return new Stone(Symbol.A);
            }
    }
    
    @Override
    public String toString() {
        return "{" + this.symbol.toString() + "," + this.index + "}"; 
    }
    
    private class StoneOffBoardException extends Exception {
        public StoneOffBoardException(Stone stone) {
            super("Stone off the board: " + stone.toString());
        }
    }
    
}
