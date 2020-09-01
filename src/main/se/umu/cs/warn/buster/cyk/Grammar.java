package se.umu.cs.warn.buster.cyk;

import java.util.HashMap;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Main class for running tests on different implementations of CYK algorithm.
 */
public class Grammar {

    private char start;
    private char[] nonTerminals;
    private char[] terminals;
    private HashMap<Character, String[]> nonTerminalProductions;
    private HashMap<Character, Character[]> terminalProductions;

    private final static char EOF = (char) -1;

    public Grammar() {
        start = EOF;
        nonTerminals = null;
        terminals = null;
        nonTerminalProductions = new HashMap<>();
        terminalProductions = new HashMap<>();
    }

    public char[] produceRandom(char production, int length) {

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

    private boolean charArrayContains(char[] array, char element) {
        for (char c : array)
            if (element == c) return true;
        return false;
    }

    private boolean setUpCorrectly() {

        if (start < 'A' || start > 'Z')
            return false;

        for (char terminal : terminals) {
            if (terminal < 'a' || terminal > 'z')
                return false;
        }

        for (char nonTerminal : nonTerminals) {
            if (nonTerminal < 'A' || nonTerminal > 'Z')
                return false;
        }

        if (!charArrayContains(nonTerminals, start))
            return false;

        return true;
    }

    public char getStart() {
        return start;
    }

    public char[] getNonTerminals() {
        return nonTerminals;
    }

    public char[] getTerminals() {
        return terminals;
    }

    public void setStart(char start) throws IllegalArgumentException {
        if (start < 'A' || start > 'Z')
            throw new IllegalArgumentException("Error with start symbol: " + start + "\nStart symbol must be a char" +
                    " between capital A-Z");

        if (nonTerminals != null && !charArrayContains(nonTerminals, start))
            throw new IllegalArgumentException("Error with start symbol: " + start + "\nNon terminal symbols are " +
                    "already set and start symbol must be one of those non terminals. " +
                    "Non terminals are: \"" + nonTerminals.toString() + "\"");

        this.start = start;
    }

    public void setNonTerminals(char[] nonTerminals) throws IllegalArgumentException {
        for (char c : nonTerminals) {
            if (c < 'A' || c > 'Z')
                throw new IllegalArgumentException("Error non terminal symbol: " + start + "\nAll non terminals must" +
                        " be a char between capital A-Z");

            if (start != EOF && !charArrayContains(nonTerminals, start))
                throw new IllegalArgumentException("Error with start symbol: " + start + "\nNon terminal symbols are " +
                        "already set and start symbol must be one of those non terminals. " +
                        "Non terminals are: \"" + nonTerminals.toString() + "\"");
        }

        this.nonTerminals = nonTerminals;
    }

    public void setTerminals(char[] terminals) throws IllegalArgumentException {
        for (char terminal : terminals) {
            if (terminal < 'a' || terminal > 'z')
                throw new IllegalArgumentException("Error terminal symbol: " + terminal + "\nAll terminals must be a " +
                        "char between lower case a-z");
        }
        this.terminals = terminals;
    }

    public void addProductionRule(char from, String production) {
        if (production.length() != 2)
            throw new IllegalArgumentException("Error from production rule: " + from + " -> " + production +
                    "\nProduction does not follow CNF");

        if (!charArrayContains(nonTerminals, from))
            throw new IllegalArgumentException("Error from: " + from + "\nIs not a member of non terminal " +
                    "productions: " + production);

        for (int i = 0; i < production.length(); i++) {
            if (!charArrayContains(nonTerminals, production.charAt(i)))
                throw new IllegalArgumentException("Error from: " + production.charAt(i) + "\nProduction is not a " +
                        "member of non terminal productions: " + nonTerminalProductions);
        }

        String[] newProductions;
        if (nonTerminalProductions.containsKey(from)) {
            String[] previousProductions = nonTerminalProductions.get(from);
            newProductions = new String[previousProductions.length + 1];
            System.arraycopy(previousProductions, 0, newProductions, 0, previousProductions.length);
            newProductions[newProductions.length - 1] = production;
        } else {
            newProductions = new String[1];
            newProductions[0] = production;
        }
        nonTerminalProductions.put(from, newProductions);
    }

    public void addProductionRule(char from, char production) {

        if (nonTerminals == null)
            throw new IllegalStateException("Error! Cannot add a production with no non terminals");

        if (!charArrayContains(nonTerminals, from))
            throw new IllegalArgumentException("Error from nonTerminal: " + from + "\nIs not a member of non terminal" +
                    " productions: " + nonTerminals);

        if (!charArrayContains(terminals, production))
            throw new IllegalArgumentException("Error from to production: " + production + "\nProduction is not a " +
                    "member of non terminal productions: " + nonTerminalProductions);

        Character[] newProductions;
        if (terminalProductions.containsKey(from)) {
            Character[] previousProductions = terminalProductions.get(from);
            newProductions = new Character[previousProductions.length + 1];
            for (int i = 0; i < previousProductions.length; i++) {
                newProductions[i] = previousProductions[i];
            }
            newProductions[newProductions.length - 1] = production;
        } else {
            newProductions = new Character[1];
            newProductions[0] = production;
        }
        terminalProductions.put(from, newProductions);
    }

    public String[] getNonTerminalProductions(char from) {
        if (from < 'A' || from > 'Z')
            throw new IllegalArgumentException("Error with input: " + from + "\nNon terminal symbols must bee " +
                    "between capital A-Z");
        return nonTerminalProductions.get(from);
    }

    public Character[] getTerminalProductions(char from) {
        if (from < 'A' || from > 'Z')
            throw new IllegalArgumentException("Error with input: " + from + "\nNon terminal symbols must bee " +
                    "between capital A-Z");
        return terminalProductions.get(from);
    }
}
