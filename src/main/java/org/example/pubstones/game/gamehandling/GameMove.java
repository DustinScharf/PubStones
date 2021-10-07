package org.example.pubstones.game.gamehandling;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;

public abstract class GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { false, false, false };
    
    private MoveKind moveKind;
    
    public GameMove(MoveKind moveKind) {
        this.moveKind = moveKind;
    }
    
    public MoveKind getMoveKind() {
        return this.moveKind;
    }
    
    public boolean isMoveKind(MoveKind moveKind) {
        return this.moveKind.equals(moveKind);
    }
    
    public abstract void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException;
    
    public static GameMove getMove(MoveKind moveKind, Object[] args) {
        Constructor<?> constructor = null;
        try {
            constructor = moveKind.getMoveClass().getConstructor(moveKind.getArgs());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SecurityException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            return (GameMove) constructor.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    /**
     * Default allowed game player move stats (all false)
     * @return
     */
    public static boolean[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
}
