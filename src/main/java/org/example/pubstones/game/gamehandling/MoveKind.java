package org.example.pubstones.game.gamehandling;

public enum MoveKind {
    
    Place("Place"), Swap("Swap"), Turn("Turn");
    
    private String name;
    
    private MoveKind(String name) {
        this.name = name;
    }
    
    /**
     * This move kind's name
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
}
