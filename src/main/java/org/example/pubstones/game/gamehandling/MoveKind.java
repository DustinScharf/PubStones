package org.example.pubstones.game.gamehandling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.gamehandling.gamemoves.*;

public enum MoveKind {
    
    Place("Place", PlaceMove.class, PlaceMove.getAllowedGamePlayerMoveStates()),
    Swap("Swap", SwapMove.class, SwapMove.getAllowedGamePlayerMoveStates()), 
    Turn("Turn", TurnMove.class, TurnMove.getAllowedGamePlayerMoveStates()),
    Challenge("Challenge", ChallengeMove.class, ChallengeMove.getAllowedGamePlayerMoveStates()),
    Boast("Boast", BoastMove.class, BoastMove.getAllowedGamePlayerMoveStates());
    
    private String name;
    private Class<?> moveClass;
    private Class<?>[] args;
    private boolean[] allowedGamePlayerMoveStates;
    
    private MoveKind(String name, Class<?> moveClass, boolean[] allowedGamePlayerMoveStates) {
        this.name = name;
        this.moveClass = moveClass;
        this.args = moveClass.getConstructors()[0].getParameterTypes();
        this.allowedGamePlayerMoveStates = allowedGamePlayerMoveStates;
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
    
    public boolean isPossible(boolean[] gamePlayerMoveStates) {
        if(gamePlayerMoveStates.length != allowedGamePlayerMoveStates.length){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < gamePlayerMoveStates.length; i++) {
            if (gamePlayerMoveStates[i] != allowedGamePlayerMoveStates[i]) {
                return false;
            }
        }
        return true;
    }
    
}
