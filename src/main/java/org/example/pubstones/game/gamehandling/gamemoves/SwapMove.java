package org.example.pubstones.game.gamehandling.gamemoves;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;
import org.example.pubstones.game.gamehandling.GameMove;
import org.example.pubstones.game.gamehandling.MoveKind;

public class SwapMove extends GameMove {

    private Stone stone1;
    private Stone stone2;
    
    /**
     * Creates a new swap move with the given target indexes and move number
     * @param index1
     * @param index2
     * @param number
     */
    public SwapMove(Stone stone1, Stone stone2, int number) {
        super(MoveKind.Swap, number);
        this.stone1 = stone1;
        this.stone2 = stone2;
    }
    
    @Override
    public void applyMove(GameField gameField) throws StoneNotFoundException, StonesEqualException {
        gameField.trySwapStones(this.stone1, this.stone2);
    }
    
    /**
     * This move's stone1
     * @return
     */
    public Stone getStone1() {
        return this.stone1;
    }
    
    /**
     * This move's stone2
     * @return
     */
    public Stone getStone2() {
        return this.stone2;
    }
    
}
