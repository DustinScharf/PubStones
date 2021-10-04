package org.example.pubstones.game.gamehandling;

public enum MoveKind {
    
    Place("Place"), Swap("Swap");
    
    private String name;
    
    private MoveKind(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
}
