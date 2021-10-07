package org.example.pubstones.game.boardpieces;

import org.example.pubstones.game.boardpieces.exceptions.StoneAlreadyContainedException;
import org.example.pubstones.game.boardpieces.exceptions.StoneLineFullException;
import org.example.pubstones.game.boardpieces.exceptions.StoneNotFoundException;
import org.example.pubstones.game.boardpieces.exceptions.StonesEqualException;

public class GameField {
    
    private StoneLine stoneLine;
    private StonePile stonePile;
    
    public GameField() {
        this.stoneLine = new StoneLine();
        this.stonePile = new StonePile();
    }

    public StoneLine getStoneLine() {
        return stoneLine;
    }

    public StonePile getStonePile() {
        return stonePile;
    }

    /**
     * Tries to place a stone from the pile on the given index
     * @param symbol
     * @param index
     * @throws StoneNotFoundException
     * @throws StoneLineFullException
     * @throws StoneAlreadyContainedException
     */
    public void tryPlaceStone(Stone stone, int index) throws StoneLineFullException, StoneNotFoundException, StoneAlreadyContainedException {
        stoneLine.placeStone(stonePile.takeStone(stone), index);
    }
    
    /**
     * Tries to swap the given stones 
     * @param index1
     * @param index2
     * @throws StonesEqualException
     * @throws StoneNotFoundException
     */
    public void trySwapStones(Stone stone1, Stone stone2) throws StoneNotFoundException, StonesEqualException {
        this.stoneLine.swapStones(stone1, stone2);
    }
    
    /**
     * Tries to turn the given stone
     * @param stone
     * @throws StoneNotFoundException
     */
    public void tryTurnStones(Stone stone) throws StoneNotFoundException {
        this.stoneLine.turnStone(stone);
    }
    
}
