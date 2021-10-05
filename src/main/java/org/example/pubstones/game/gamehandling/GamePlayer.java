package org.example.pubstones.game.gamehandling;

public class GamePlayer {
    
    private String name;
    private long id = 0;
    private int score;
    private boolean current = false;
    
    /**
     * Creates a new player with the given name
     * @param name
     */
    public GamePlayer(String name) {
        this.name = name;
        // TODO randomize id
    }
    
    public String getName() {
        return this.name;
    }
    
    /**
     * This player's id
     * @return
     */
    public long getID() {
        return this.id;
    }
    
    /**
     * Resets this player's score
     */
    public void resetScore() {
        this.score = 0;
    }
    
    /**
     * Increases this player's score by 1
     */
    public void increaseScore() {
        this.score++;
    }
    
    /**
     * Checks whether this player reached the given targetScore
     * @param targetScore
     * @return
     */
    public boolean reachedScore(int targetScore) {
        return this.score >= targetScore;
    }
    
    /**
     * Checks whether this player is the current player
     * @return
     */
    public boolean isCurrent() {
        return this.current;
    }
    
    /**
     * Sets whether this player is the current player
     * @param current
     * @return
     */
    public boolean setCurrent(boolean current) {
        this.current = current;
        return this.current;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GamePlayer)) {
            return false;
        }
        return this.id == ((GamePlayer)obj).getID();
    }
    
}
