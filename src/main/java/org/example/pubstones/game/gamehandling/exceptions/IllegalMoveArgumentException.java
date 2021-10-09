package org.example.pubstones.game.gamehandling.exceptions;

public class IllegalMoveArgumentException extends Exception {
    private final static String message = "This argument is illegal: ";
    private Class<?> argument;
    
    public IllegalMoveArgumentException(Class<?> argument) {
        super(message + argument.toString());
    }
    
    public Class<?> getArgument() {
        return this.argument;
    }

}
