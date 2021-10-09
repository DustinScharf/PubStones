package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameHandler;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.GamePlayer;
import org.example.pubstones.game.gamehandling.MoveKind;
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class ChallengeBoastMove extends GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { true, true, false, false, false };

    private GamePlayer targetPlayer;
    
    protected ChallengeBoastMove(GamePlayer targetPlayer) {
        super(MoveKind.ChallengeBoast);
        this.targetPlayer = targetPlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException, StoneAlreadyContainedException {
        this.disableFirstPlayer();
        this.targetPlayer.setChallengedBoast(true);
    }

    public GamePlayer getTargetPlayer() {
        return this.targetPlayer;
    }
    
    @Override
    public boolean isInitialized() {
        if (targetPlayer == null) {
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
        this.targetPlayer = gamePlayer;
        return this;
    }

    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Symbol.class);
    }
    
}
