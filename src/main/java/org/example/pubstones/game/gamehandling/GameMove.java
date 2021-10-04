package org.example.pubstones.game.gamehandling;

import org.example.pubstones.game.boardpieces.Symbol;

public class GameMove {
    
    private int number;
    private MoveKind moveKind;
    
    public GameMove(Symbol symbol, int number) {
        this.number = number;
        this.moveKind = MoveKind.Place;
    }
    
    public GameMove(int index1, int index2, int number) {
        this.number = number;
        this.moveKind = MoveKind.Swap;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public MoveKind getMoveKind() {
        return this.moveKind;
    }
    
    public boolean isMoveKind(MoveKind moveKind) {
        return this.moveKind.equals(moveKind);
    }
    
}
