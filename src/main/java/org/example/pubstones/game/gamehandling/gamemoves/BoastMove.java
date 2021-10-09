package org.example.pubstones.game.gamehandling.gamemoves;

import java.util.ArrayList;
import java.util.Arrays;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class BoastMove extends GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { false, false, false };
    
    private ArrayList<Symbol> symbols;
    private GamePlayer gamePlayer;

    public BoastMove() {
        super(MoveKind.Boast);
    }
    
    /**
     * Creates a new boast move
     * @param moveKind
     */
    public BoastMove(ArrayList<Symbol> symbols, GamePlayer gamePlayer) {
        super(MoveKind.Boast);
        this.symbols = symbols;
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        gameHandler.tryBoast(this.symbols, this.gamePlayer);
    }
    
    public ArrayList<Symbol> getSymbol() {
        return this.symbols;
    }
    
    public GamePlayer getPlayer() {
        return this.gamePlayer;
    }
    
    public static boolean[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }

    @Override
    public boolean isInitialized() {
        if (this.symbols == null) {
            return false;
        }
        if (this.symbols.size() == 0) {
            return false;
        }
        if (this.gamePlayer == null) {
            return false;
        }
        return true;
    }

    @Override
    public GameMove stone(Stone stone) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Stone.class);
    }

    @Override
    public GameMove index(int index) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Integer.class);
    }

    @Override
    public GameMove player(GamePlayer gamePlayer) throws IllegalMoveArgumentException {
        this.gamePlayer = gamePlayer;
        return this;
    }
    
    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        this.symbols.add(symbol);
        return this;
    }
    
}
