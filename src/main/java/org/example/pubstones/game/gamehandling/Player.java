package org.example.pubstones.game.gamehandling;

public class Player {
    
    private String name;
    private long id = 0;
    private int score;
    private boolean current = false;
    
    public Player(String name) {
        this.name = name;
        // TODO randomize id
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getID() {
        return this.id;
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public void increaseScore() {
        this.score++;
    }
    
    public boolean reachedScore(int targetScore) {
        return this.score >= targetScore;
    }
    
    public boolean isCurrent() {
        return this.current;
    }
    
    public boolean setCurrent(boolean current) {
        this.current = current;
        return this.current;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        return this.id == ((Player)obj).getID();
    }
    
}
