package org.example.pubstones.game.gamehandling;

import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.gamehandling.gamemoves.PlaceMove;
import org.example.pubstones.game.gamehandling.gamemoves.SwapMove;
import org.example.pubstones.game.gamehandling.gamemoves.TurnMove;

public enum MoveKind {
    
    Place("Place", PlaceMove.class),
    Swap("Swap", SwapMove.class), 
    Turn("Turn", TurnMove.class);
    
    private String name;
    private Class<?> moveClass;
    private Class<?>[] args;
    
    private MoveKind(String name, Class<?> moveClass) {
        this.name = name;
        this.moveClass = moveClass;
        this.args = moveClass.getConstructors()[0].getParameterTypes();
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
    
    public Class<?> getMoveClass() {
        return this.moveClass;
    }
    
    public Class[] getArgs() {
        return this.args;
    }
    
}
