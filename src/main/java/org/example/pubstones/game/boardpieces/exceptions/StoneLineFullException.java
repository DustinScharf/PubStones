package org.example.pubstones.game.boardpieces.exceptions;

public class StoneLineFullException extends Exception {
    private final static String message = "StoneLine is full: ";
    
    public StoneLineFullException(Class<?> c) {
        super(message + c.toString());
    }
    public StoneLineFullException(Class<?> c, String message) {
        super(message + c.toString() + ": " + message);
    }
}
