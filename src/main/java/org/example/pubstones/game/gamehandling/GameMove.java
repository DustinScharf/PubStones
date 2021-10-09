package org.example.pubstones.game.gamehandling;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.*;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;
import org.example.pubstones.game.gamehandling.exceptions.MissingMoveArgumentException;

public abstract class GameMove {
    private static int[] allowedGamePlayerMoveStates = new int[] { 0, 0, 0, 0, 0 };
    
    private MoveKind moveKind;
    protected GamePlayer senderPlayer;
    
    /**
     * Creates a new game move with the given move kind 
     * @param moveKind
     */
    protected GameMove(MoveKind moveKind) {
        this.moveKind = moveKind;
    }
    
    /**
     * This move's move kind
     * @return
     */
    public MoveKind getMoveKind() {
        return this.moveKind;
    }
    
    /**
     * Checks whether this move is an instance of the given move kind
     * @param moveKind
     * @return
     */
    public boolean isMoveKind(MoveKind moveKind) {
        return this.moveKind.equals(moveKind);
    }
    
    /**
     * This move's move kind's argument classes 
     * Same as MoveKind.getArgs();
     * @return
     */
    public Class<?>[] getArgumentClasses() {
        return this.moveKind.getArgs();
    }
    
    /**
     * Checks whether the given class is an argument of this game move
     * @param c
     * @return
     */
    public boolean containsArgumentClass(Class<?> c) {
        return this.moveKind.containsArgumentClass(c);
    }
    
    /**
     * Disables first player for the sender
     */
    protected void disableFirstPlayer() {
        this.senderPlayer.setFirstPlayer(false);
    }
    
    /**
     * Applies this move onto the given game handler
     * @param gameField
     * @throws StoneLineFullException
     * @throws StoneNotFoundException
     * @throws StonesEqualException
     * @throws MissingMoveArgumentException
     */
    public abstract void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException, StoneAlreadyContainedException, MissingMoveArgumentException;

    /**
     * Checks whether this game move is fully initialized
     * @return
     */
    public abstract boolean isInitialized();
    
    /**
     * Sets a new sender player
     * @param senderPlayer
     * @return
     */
    public GameMove sender(GamePlayer senderPlayer) {
        this.senderPlayer = senderPlayer;
        return this;
    }
    
    /**
     * This game move's sender player
     * @return
     */
    public GamePlayer getSenderPlayer() {
        return this.senderPlayer;
    }
    
    /**
     * Sets a new stone for this game move (exact function may vary in different move kinds)
     * @param stone
     * @return
     * @throws IllegalMoveArgumentException
     */
    public abstract GameMove stone(Stone stone) throws IllegalMoveArgumentException;

    /**
     * Sets a new index for this game move (exact function may vary in different move kinds)
     * @param index
     * @return
     * @throws IllegalMoveArgumentException
     */
    public abstract GameMove index(int index) throws IllegalMoveArgumentException;

    /**
     * Sets a new game player for this game move (exact function may vary in different move kinds)
     * @param gamePlayer
     * @return
     * @throws IllegalMoveArgumentException
     */
    public abstract GameMove player(GamePlayer gamePlayer) throws IllegalMoveArgumentException;
    
    /**
     * Sets a new symbol for this game move (exact function may vary in different move kinds)
     * @param symbol
     * @return
     * @throws IllegalMoveArgumentException
     */
    public abstract GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException;
    
    /**
     * Creates an empty game move with the given move kind
     * @param moveKind
     * @return
     */
    public static GameMove getMove(MoveKind moveKind) {
        Constructor<?> constructor = null;
        try {
            constructor = moveKind.getMoveClass().getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SecurityException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            return (GameMove) constructor.newInstance();
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
    
    @Deprecated
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
    public static int[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
}
