package org.example.pubstones.game.gamehandling.gamemoves;

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

public class BoastMove extends GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { false, false, false };
    
    private Symbol[] symbols;
    private GamePlayer gamePlayer;
    
    /**
     * Creates a new boast move
     * @param moveKind
     */
    public BoastMove(Symbol[] symbols, GamePlayer gamePlayer) {
        super(MoveKind.Boast);
        this.symbols = symbols;
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        gameHandler.tryBoast(this.symbols, this.gamePlayer);
    }
    
    public Symbol[] getSymbol() {
        return this.symbols;
    }
    
    public GamePlayer getPlayer() {
        return this.gamePlayer;
    }
    
    public static boolean[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }
    
}
