package org.example.pubstones.game.boardpieces.exceptions;

public class StoneNotFoundException extends Exception {
    private final static String message = "Stone is not found in: ";
    
    public StoneNotFoundException(Class<?> c, String messageEx) {
        super(message + c.toString() + ": " + messageEx);
    }
    
    public StoneNotFoundException(Class<?> c, int messageInt) {
        super(message + c.toString() + ": " + messageInt);
    }
}
