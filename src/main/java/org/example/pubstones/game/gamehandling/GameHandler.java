package org.example.pubstones.game.gamehandling;

import java.util.ArrayList;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.gamehandling.gamemoves.*;
import org.example.pubstones.util.datatype.Queue;

public class GameHandler {
    
    private ArrayList<GamePlayer> players;
    private MoveHistory moveHistory;
    
    private Queue<GamePlayer> playerQueue;
    
    private GameField gameField;
    
    public GameHandler() {
        this.players = new ArrayList<GamePlayer>();
        this.playerQueue = new Queue<GamePlayer>();
    }
    
    public void addPlayer(GamePlayer gamePlayer) {
        this.players.add(gamePlayer);
        this.playerQueue.enqueue(gamePlayer);
    }
    
    public void removePlayer(GamePlayer gamePlayer) {
        this.players.remove(gamePlayer);
        this.playerQueue.remove(gamePlayer);
    }
    
    public GameField getCurrentState() {
        return this.gameField;
    }
    
    public void receiveGameMove(GameMove gameMove) {
        gameMove.applyMove(this.gameField);
        moveHistory.add(gameMove);
    }
    
    public GamePlayer nextPlayer() {
        GamePlayer currentLastPlayer = this.playerQueue.dequeue();
        this.playerQueue.enqueue(currentLastPlayer);
        return this.playerQueue.first();
    }
    
    public GamePlayer getCurrentPlayer() {
        return this.playerQueue.first();
    }
    
}
