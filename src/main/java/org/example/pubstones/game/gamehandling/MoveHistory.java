package org.example.pubstones.game.gamehandling;

import java.util.ArrayList;

public class MoveHistory {
    
    private ArrayList<GameMove> moves;
    
    /**
     * Creates a new move history
     */
    public MoveHistory() {
        this.moves = new ArrayList<GameMove>();
    }
    
    /**
     * This history's move count
     * @return
     */
    public int getMoveCount() {
        return this.moves.size();
    }
    
    /**
     * Adds the given move to the history
     * @param move
     */
    public void add(GameMove move) {
        this.moves.add(move);
    }
    
    /**
     * The last move in this history
     * @return
     */
    public GameMove last() {
        return this.moves.get(this.moves.size() - 1);
    }
    
}
