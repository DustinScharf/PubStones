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
import org.example.pubstones.game.gamehandling.exceptions.IllegalMoveArgumentException;

public class PerformChallengeMove extends GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { true, true, true, false, false };
    
    private Symbol symbol;
    private Stone stone;
    private GamePlayer targetPlayer;
    private GamePlayer challengerPlayer;

    private boolean firstPlayer = true;

    public PerformChallengeMove() {
        super(MoveKind.PerformChallenge);
    }

    public PerformChallengeMove(Symbol symbol, Stone stone, GamePlayer targetPlayer, GamePlayer challengerPlayer) {
        super(MoveKind.PerformChallenge);
        this.symbol = symbol;
        this.stone = stone;
        this.targetPlayer = targetPlayer;
        this.challengerPlayer = challengerPlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        this.disableFirstPlayer();
        boolean result = gameHandler.checkChallenge(this.symbol, this.stone);
        if (result) {
            this.senderPlayer.increaseScore();
        } else {
            this.challengerPlayer.increaseScore();
        }
        this.senderPlayer.setChallenged(false);
    }

    /**
     * This move's symbol
     * @return
     */
    public Symbol getSymbol() {
        return this.symbol;
    }

    /**
     * This move's stone
     * @return
     */
    public Stone getStone() {
        return this.stone;
    }

    /**
     * This move's target game player
     * @return
     */
    public GamePlayer getTargetPlayer() {
        return this.targetPlayer;
    }

    /**
     * This move's challenger game player
     * @return
     */
    public GamePlayer getChallengerPlayer() {
        return this.challengerPlayer;
    }

    public static boolean[] getAllowedGamePlayerMoveStates() {
        return allowedGamePlayerMoveStates;
    }

    @Override
    public boolean isInitialized() {
        if (this.symbol == null) {
            return false;
        }
        if (this.stone == null) {
            return false;
        }
        if (this.challengerPlayer == null) {
            return false;
        }
        if (this.targetPlayer == null) {
            return false;
        }
        return true;
    }

    @Override
    public GameMove stone(Stone stone) throws IllegalMoveArgumentException {
        this.stone = stone;
        return this;
    }

    @Override
    public GameMove index(int index) throws IllegalMoveArgumentException {
        throw new IllegalMoveArgumentException(Integer.class);
    }

    @Override
    public GameMove player(GamePlayer gamePlayer) throws IllegalMoveArgumentException {
        if (this.firstPlayer) {
            this.challengerPlayer = gamePlayer;
        } else {
            this.targetPlayer = gamePlayer;
        }
        this.firstPlayer = !this.firstPlayer;
        return this;
    }

    @Override
    public GameMove symbol(Symbol symbol) throws IllegalMoveArgumentException {
        this.symbol = symbol;
        return this;
    }

}
