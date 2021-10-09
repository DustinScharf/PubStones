package org.example.pubstones.game.gamehandling;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import org.example.pubstones.game.boardpieces.GameField;
import org.example.pubstones.game.boardpieces.Stone;
import org.example.pubstones.game.boardpieces.Symbol;
import org.example.pubstones.game.boardpieces.exceptions.*;
import org.example.pubstones.game.gamehandling.exceptions.MissingMoveArgumentException;
import org.example.pubstones.game.gamehandling.gamemoves.*;
import org.example.pubstones.util.datatype.Queue;

public class GameHandler {
    public static final int WINNING_SCORE = 3;
    
    private ArrayList<GamePlayer> players;
    private MoveHistory moveHistory;
    
    private Queue<GamePlayer> playerQueue;
    
    private GameField gameField;
    
    public GameHandler() {
        this.init();
    }

    private void init() {
        this.gameField = new GameField();
        this.players = new ArrayList<GamePlayer>();
        this.playerQueue = new Queue<GamePlayer>();
        this.moveHistory = new MoveHistory();
        try {
            this.gameField.tryPlaceStone(this.gameField.getStonePile().getStones().get(0), 0);
        } catch (StoneLineFullException | StoneNotFoundException | StoneAlreadyContainedException e) {
            e.printStackTrace();
        }
        this.getCurrentPlayer().setFirstPlayer(true);
        this.getCurrentPlayer().setCurrent(true);
    }
    
    public ArrayList<GamePlayer> getPlayers() {
        return players;
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
    
    public boolean isGameOver() {
        for (GamePlayer player : players) {
            if (player.reachedScore(WINNING_SCORE)) {
                return true;
            }
        }
        return false;
    }
    
    public GamePlayer getLeadingPlayer() {
        if (players.size() <= 0) {
            // TODO Exception
            return null;
        }
        GamePlayer lead = players.get(0);
        for (GamePlayer player : players) {
            if (lead.getScore() < player.getScore()) {
                lead = player;
            }
        }
        return lead;
    }
    
    public void receiveGameMove(GameMove gameMove) throws IllegalArgumentException, StoneLineFullException, StoneNotFoundException, StonesEqualException, StoneAlreadyContainedException, MissingMoveArgumentException {
        if (gameMove == null) {
            throw new IllegalArgumentException("GameMove gameMove can not be null");
        }
        if (!gameMove.isInitialized()) {
            throw new MissingMoveArgumentException(gameMove.getArgumentClasses());
        }
        gameMove.applyMove(this);
        gameMove.getSenderPlayer().setCurrent(false);
        moveHistory.add(gameMove);
        this.playerQueue.enqueue(this.playerQueue.dequeue());
        this.playerQueue.first().setCurrent(true);
    }
    
    public GamePlayer getNextPlayer() {
        return this.playerQueue.second();
    }
    
    public GamePlayer getCurrentPlayer() {
        return this.playerQueue.first();
    }
    
    public static GameMove getGameMove(MoveKind moveKind) {
        return GameMove.getMove(moveKind);
    }
    
    public boolean checkChallenge(Symbol symbol, Stone stone) {  
        return stone.getSymbol().equals(symbol);
    }
    
    public boolean checkBoast(ArrayList<Symbol> symbols) {
        for (int i = 0; i < symbols.size(); i++) {
            if (!this.gameField.getStoneLine().getStone(i).getSymbol().equals(symbols.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    @Deprecated
    public static GameMove getGameMove(MoveKind moveKind, Object... args) throws IllegalArgumentException {
        return GameMove.getMove(moveKind, args);
    }
    
}
