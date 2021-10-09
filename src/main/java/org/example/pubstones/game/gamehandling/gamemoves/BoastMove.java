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
    private static int[] allowedGamePlayerMoveStates = new int[] { 0, 1, -1, 1, 1 };

    private ArrayList<Symbol> symbols;
    private GamePlayer challengerPlayer;

    public BoastMove() {
        super(MoveKind.Boast);
        this.symbols = new ArrayList<>();
    }
    
    /**
     * Creates a new boast move
     * @param moveKind
     */
    public BoastMove(ArrayList<Symbol> symbols, GamePlayer challengerPlayer) {
        super(MoveKind.Boast);
        this.symbols = symbols;
        this.challengerPlayer = challengerPlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        this.disableFirstPlayer();
        boolean result = gameHandler.checkBoast(this.symbols);
        if (result) {
            for (int i = 0; i < GameHandler.WINNING_SCORE; i++){
                this.senderPlayer.increaseScore();
            }
        } else {
            for (int i = 0; i < GameHandler.WINNING_SCORE; i++) {
                this.challengerPlayer.increaseScore();
            }
        }
        this.senderPlayer.setChallengedBoast(false);
    }
    
    public ArrayList<Symbol> getSymbols() {
        return this.symbols;
    }
    
    public GamePlayer getChallengerPlayer() {
        return this.challengerPlayer;
    }
    
    public static int[] getAllowedGamePlayerMoveStates() {
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
        if (this.challengerPlayer == null) {
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
        this.challengerPlayer = gamePlayer;
        return this;
    }
    
    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        this.symbols.add(symbol);
        return this;
    }
    
}
