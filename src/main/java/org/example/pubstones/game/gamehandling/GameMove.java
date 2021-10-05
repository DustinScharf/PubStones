package org.example.pubstones.game.gamehandling;

import org.example.pubstones.game.boardpieces.GameField;

public abstract class GameMove {
    
    private int number;
    private MoveKind moveKind;
    
    public GameMove(MoveKind moveKind, int number) {
        this.number = number;
        this.moveKind = moveKind;
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
    
    public abstract void applyMove(GameField gameField);
    
}