package se.umu.cs.warn.buster.cyk;

import java.util.HashMap;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Main class for running tests on different implementations of CYK algorithm.
 */
public abstract class Grammar {

    private char start;
    private char[] nonTerminals;
    private char[] terminals;
    private HashMap<Character, String[]> nonTerminalProductions;
    private HashMap<Character, Character[]> terminalProductions;

    public char[] produceRandom(char production) {

        if (production < 'A' || production > 'Z')
            throw new IllegalArgumentException("Error producing! Must produce from a nonTerminal, which must be " +
                    "between capital ascii char between A-Z");
        int loc = -1;

        return null;
    }

    public String findCartesianProductFromProduction (char[] produced) {

        return null;
    }

    private void findNonTerminalFromProduction(char c) {
    }

    private void findNonTerminalFromProduction(String s) {

    }

    public void setStart(char start) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z')
            throw new IllegalArgumentException("Error with start symbol: " + start + "\nStart symbol must be a char" +
                    " between capital A-Z");
        this.start = start;
    }

    public void setNonTerminals(char[] nonTerminals) throws IllegalArgumentException {
        for (char c : nonTerminals) {
            if (c < 'A' || c > 'Z')
                throw new IllegalArgumentException("Error non terminal symbol: " + start + "\nAll non terminals must" +
                        " be a char between capital A-Z");
        }

        this.nonTerminals = nonTerminals;
    }

    public void setTerminals(char[] terminals) throws IllegalArgumentException {
        if (start < 'a' || start > 'a')
            throw new IllegalArgumentException("Error terminal symbol: " + start + "\nAll terminals must be a char" +
                    " between lower case a-z");
        this.terminals = terminals;
    }
}
