package org.example.pubstones.game.gamehandling;

import java.util.ArrayList;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.gamehandling.gamemoves.*;

public class GameHandler {
    
    private ArrayList<GamePlayer> players;
    private MoveHistory moveHistory;
    
    private GameField gameField;
    
    public GameField getCurrentState() {
        return this.gameField;
    }
    
    public void receiveGameMove(GameMove gameMove) {
        gameMove.applyMove(this.gameField);
        moveHistory.add(gameMove);
    }
    
}
