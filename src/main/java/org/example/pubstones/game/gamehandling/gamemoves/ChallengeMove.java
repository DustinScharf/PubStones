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

public class ChallengeMove extends GameMove {
    private static boolean[] allowedGamePlayerMoveStates = new boolean[] { false, false, false };
    
    private Symbol symbol;
    private Stone stone;
    private GamePlayer targetPlayer;
    private GamePlayer challengerPlayer;
    
    public ChallengeMove(Symbol symbol, Stone stone, GamePlayer targetPlayer, GamePlayer challengerPlayer) {
        super(MoveKind.Challenge);
        this.symbol = symbol;
        this.stone = stone;
        this.targetPlayer = targetPlayer;
        this.challengerPlayer = challengerPlayer;
    }

    @Override
    public void applyMove(GameHandler gameHandler) throws StoneLineFullException, StoneNotFoundException, StonesEqualException {
        gameHandler.tryChallenge(this.symbol, this.stone, this.targetPlayer, this.challengerPlayer);
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
    
}
