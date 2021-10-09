package org.example.pubstones.game.gamehandling.exceptions;

import java.util.ArrayList;

public class MissingMoveArgumentException extends Exception {
    private final static String message = "There are arguments missing!";
    private Class<?>[] neededArguments;
    
    public MissingMoveArgumentException(Class<?>... neededArguments) {
        super(message);
        this.neededArguments = neededArguments;
    }
    
}
