package org.example.pubstones.game.boardpieces.exceptions;

import org.example.pubstones.game.boardpieces.Stone;

public class StonesEqualException extends Exception {
    private final static String message = "Stones are equal in: ";
    
    public StonesEqualException(Class<?> c, Stone stone1, Stone stone2) {
        super(message + c.toString() + ": " + stone1 + " | " + stone2);
    }
    
}
