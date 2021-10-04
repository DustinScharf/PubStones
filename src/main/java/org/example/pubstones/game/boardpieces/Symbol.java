package org.example.pubstones.game.boardpieces;

public enum Symbol {
    
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G");

    String name;
    
    private Symbol(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    /**
     * A random symbol
     * @return
     */
    public static Symbol getRandom() {
        // TODO actual random function missing
        return A;
    }
    
}
