package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;

public class TurnMove extends GameMove {

    private Stone stone;
    
    public TurnMove(Stone stone) {
        super(MoveKind.Turn);
        this.stone = stone;
    }
    
    @Override
    public void applyMove(GameField gameField) throws StoneNotFoundException {
        gameField.tryTurnStones(this.stone);
    }
    
    /**
     * This move's stone
     * @return
     */
    public Stone getStone() {
        return this.stone;
    }
    
}
