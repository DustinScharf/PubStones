package org.example.pubstones.game.gamehandling;

import java.util.ArrayList;

import org.example.pubstones.game.boardpieces.Stone;

public class GamePlayer {
    
    private String name;
    private long id = 0;
    private int score;
    
    private boolean firstPlayer = false;
    
    private boolean current = false;
    private Stone challengedStone = null;
    
    private boolean challengedBoast = false;
    private boolean dismissedBoastOn = false;
    
    
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
     * This player's score
     * @return
     */
    public int getScore() {
        return this.score;
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
    
    public boolean isFirstPlayer() {
        return this.firstPlayer;
    }
    
    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }
    
    public boolean isChallenged() {
        return this.challengedStone != null;
    }
    
    public void setChallenged(Stone challengedStone) {
        this.challengedStone = challengedStone;
    }
    
    public Stone getChallengedStone() {
        return this.challengedStone;
    }
    
    public boolean isChallengedBoast() {
        return this.challengedBoast;
    }
    
    public void setChallengedBoast(boolean challengedBoast) {
        this.challengedBoast = challengedBoast;
    }
    
    public boolean isDismissedBoastOn() {
        return this.dismissedBoastOn;
    }
    
    public void setDismissedBoastOn(boolean dismissedBoastOn) {
        this.dismissedBoastOn = dismissedBoastOn;
    }
    
    private boolean[] getMoveStates() {
        return new boolean[] {
                this.isFirstPlayer(), 
                this.isCurrent(), 
                this.isChallenged(), 
                this.isChallenged(),
                this.isDismissedBoastOn()
            };
    }
    
    public MoveKind[] getPossibleMoves() {
        ArrayList<MoveKind> moves = new ArrayList<MoveKind>();
        MoveKind[] allMoves = MoveKind.values();
        for (MoveKind move : allMoves) {
            if (move.isPossible(this.getMoveStates())) {
                moves.add(move);
            }
        }
        MoveKind[] moveOutput = new MoveKind[moves.size()];
        moveOutput = moves.toArray(moveOutput);
        return moveOutput;
    }
        
    public static boolean[] getDefaultAllowedGamePlayerMoveStates() {
        return new boolean[] { false, false, false };
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GamePlayer)) {
            return false;
        }
        return this.id == ((GamePlayer) obj).getID();
    }
    
}
