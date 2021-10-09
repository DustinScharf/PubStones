package org.example.pubstones.game.gamehandling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.gamehandling.gamemoves.*;

public enum MoveKind {
    
    Place("Place", PlaceMove.class, PlaceMove.getAllowedGamePlayerMoveStates()),
    Swap("Swap", SwapMove.class, SwapMove.getAllowedGamePlayerMoveStates()), 
    Turn("Turn", TurnMove.class, TurnMove.getAllowedGamePlayerMoveStates()),
    PerformChallenge("PerformChallenge", PerformChallengeMove.class, PerformChallengeMove.getAllowedGamePlayerMoveStates()),
    AskChallenge("Challenge", AskChallengeMove.class, AskChallengeMove.getAllowedGamePlayerMoveStates()),
    Boast("Boast", BoastMove.class, BoastMove.getAllowedGamePlayerMoveStates()),
    ChallengeBoast("ChallengeBoast", ChallengeBoastMove.class, ChallengeBoastMove.getAllowedGamePlayerMoveStates()),
    ConsentBoast("ConsentBoast", ConsentBoastMove.class, ConsentBoastMove.getAllowedGamePlayerMoveStates()),
    DismissBoast("DismissBoast", DismissBoastMove.class, DismissBoastMove.getAllowedGamePlayerMoveStates());
    
    private String name;
    private Class<?> moveClass;
    private Class<?>[] args;
    private int[] allowedGamePlayerMoveStates;
    
    private MoveKind(String name, Class<?> moveClass, int[] allowedGamePlayerMoveStates) {
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
    
    public Class<?>[] getArgs() {
        return this.args;
    }
    
    /**
     * Checks whether the given class is an argument of this move kind
     * @param cls
     * @return
     */
    public boolean containsArgumentClass(Class<?> cls){
        for (Class<?> c : args) {
            if (c.equals(cls)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPossible(boolean[] gamePlayerMoveStates) {
        if(gamePlayerMoveStates.length != allowedGamePlayerMoveStates.length){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < gamePlayerMoveStates.length; i++) {
            if (allowedGamePlayerMoveStates[i] != 0) {
                if ((allowedGamePlayerMoveStates[i] == -1 && gamePlayerMoveStates[i]) || (allowedGamePlayerMoveStates[i] == 1 && !gamePlayerMoveStates[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
