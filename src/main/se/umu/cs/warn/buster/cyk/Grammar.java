package se.umu.cs.warn.buster.cyk;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Main class for running tests on different implementations of CYK algorithm.
 */
public class Grammar {

    private char start;
    private char[] nonTerminals;
    private char[] terminals;
    private String[][] nonTerminalProductions;
    private char[][] terminalProductions;


    private final static char EOF = (char) -1;

    public Grammar() {
        start = EOF;
        nonTerminals = null;
        terminals = null;
        nonTerminalProductions = null;
        terminalProductions = null;
    }

    public char[] produceRandom(char production, int length) {

        if (production < 'A' || production > 'Z')
            throwNonTerminalInvalidCharError(production);
        int loc = -1;

        return null;
    }

    public String findCartesianProductFromProduction(String produced) {

        if (produced.length() == 1) {
            if (!isTerminalProductionCharacter(produced.charAt(0)))
                throwTerminalInvalidCharError(produced.charAt(0));
            return findNonTerminalFromProduction(produced.charAt(0));
        }

        if (produced.length() != 2)
            throw new IllegalArgumentException("Error: \r\nProduced '" + produced + "' does not follow CNF");

        for (int i = 0; i < 2; i++) {
            if (!isNonTerminalProductionCharacter(produced.charAt(i)))
                throwNonTerminalInvalidCharError(produced.charAt(i));
        }
        return findNonTerminalFromProduction(produced);
    }

    public String findNonTerminalFromProduction(char produced) {

        StringBuilder cartesianProduct = new StringBuilder();
        for (int i = 0; i < terminalProductions.length; i++) {
            if (terminalProductions[i] == null)
                continue;
            for (int j = 0; j < terminalProductions[i].length; j++) {
                if (terminalProductions[i][j] == produced)
                    cartesianProduct.append(nonTerminals[i]);
            }
        }
        return cartesianProduct.toString();
    }

    private String findNonTerminalFromProduction(String produced) {

        StringBuilder cartesianProduct = new StringBuilder();
        for (int i = 0; i < nonTerminalProductions.length; i++) {
            if (nonTerminalProductions[i] == null)
                continue;
            for (int j = 0; j < nonTerminalProductions[i].length; j++) {
                if (nonTerminalProductions[i][j].equals(produced))
                    cartesianProduct.append(nonTerminals[i]);
            }
        }
        return cartesianProduct.toString();
    }

    private boolean charArrayContains(char[] array, char element) {
        for (char c : array)
            if (element == c) return true;
        return false;
    }

    private boolean setUpCorrectly() {

        if (!isNonTerminalProductionCharacter(start))
            return false;

        for (char terminal : terminals) {
            if (!isTerminalProductionCharacter(terminal))
                return false;
        }

        for (char nonTerminal : nonTerminals) {
            if (!isNonTerminalProductionCharacter(nonTerminal))
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
        if (!isNonTerminalProductionCharacter(start))
            throwNonTerminalInvalidCharError(start);

        if (nonTerminals != null && !charArrayContains(nonTerminals, start))
            throw new IllegalArgumentException("Error with start symbol: " + start + "\nNon terminal symbols are " +
                    "already set and start symbol must be one of those non terminals. " +
                    "Non terminals are: \"" + nonTerminals.toString() + "\"");

        this.start = start;
    }

    public void setNonTerminals(char[] nonTerminals) throws IllegalArgumentException {
        for (char c : nonTerminals) {
            if (!isNonTerminalProductionCharacter(c))
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
            if (!isTerminalProductionCharacter(terminal))
                throwTerminalInvalidCharError(terminal);
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
                throw new IllegalArgumentException("Error from input production characther: " + production.charAt(i) +
                        "\nProduction is not a member of non terminal characters: " + String.valueOf(nonTerminals));
        }

        if (nonTerminalProductions == null) {
            nonTerminalProductions = new String[nonTerminals.length][];
        }

        int index = nonTerminalProductionIndex(from);
        if (nonTerminalProductions[index] == null) {
            nonTerminalProductions[index] = new String[1];
            nonTerminalProductions[index][0] = production;
        } else {
            String[] temp = new String[nonTerminalProductions[index].length + 1];
            for (int i = 0; i < nonTerminalProductions[index].length; i++) {
                temp[i] = nonTerminalProductions[index][i];
            }
            temp[temp.length - 1] = production;
            nonTerminalProductions[index] = temp;
        }
    }

    public void addProductionRule(char from, char production) {

        if (nonTerminals == null)
            throw new IllegalStateException("Error! Cannot add a production with no non terminals");

        if (!charArrayContains(nonTerminals, from))
            throw new IllegalArgumentException("Error from nonTerminal: " + from + "\nIs not a member of non terminal" +
                    " productions: "
                    + (nonTerminalProductions != null ? nonTerminalProductions.toString() : "[]"));

        if (!charArrayContains(terminals, production))
            throw new IllegalArgumentException("Error from to production: " + production + "\nProduction is not a " +
                    "member of non terminal productions: "
                    + (nonTerminalProductions != null ? nonTerminalProductions.toString() : "[]"));

        if (terminalProductions == null) {
            terminalProductions = new char[nonTerminals.length][];
        }

        int index = nonTerminalProductionIndex(from);
        if (terminalProductions[index] == null) {
            terminalProductions[index] = new char[1];
            terminalProductions[index][0] = production;
        } else {
            char[] temp = new char[terminalProductions[index].length + 1];
            for (int i = 0; i < terminalProductions[index].length; i++) {
                temp[i] = terminalProductions[index][i];
            }
            temp[temp.length - 1] = production;
            terminalProductions[index] = temp;
        }
    }

    public String[] getNonTerminalProductions(char from) {
        if (!isNonTerminalProductionCharacter(from))
            throwNonTerminalInvalidCharError(from);

        int index = nonTerminalProductionIndex(from);
        if (nonTerminalProductions != null && nonTerminalProductions[index] != null)
            return nonTerminalProductions[index];

        return null;
    }

    public char[] getTerminalProductions(char from) {
        if (!isNonTerminalProductionCharacter(from))
            throwNonTerminalInvalidCharError(from);

        int index = nonTerminalProductionIndex(from);
        if (terminalProductions != null && terminalProductions[index] != null)
            return terminalProductions[index];

        return null;
    }

    private int terminalProductionIndex(char terminal) {
        for (int i = 0; i < terminals.length; i++)
            if (terminals[i] == terminal)
                return i;
        return -1;
    }

    private int nonTerminalProductionIndex(char nonTerminal) {
        for (int i = 0; i < nonTerminals.length; i++)
            if (nonTerminals[i] == nonTerminal)
                return i;
        return -1;
    }

    private boolean isTerminalProductionCharacter(char production) {
        if ((production >= 'a' && production <= 'z') || production == '(' || production == ')')
            return true;

        return false;
    }

    private boolean isNonTerminalProductionCharacter(char production) {
        if (production >= 'A' && production <= 'Z')
            return true;

        return false;
    }

    private void throwTerminalInvalidCharError(char invalidChar) {
        throw new IllegalArgumentException("Error with Terminal character: " + invalidChar +
                "\nTerminal symbols must be ascii characters between non capital a-z or parenthesis ()");
    }

    private void throwNonTerminalInvalidCharError(char invalidChar) {
        throw new IllegalArgumentException("Error with Non Terminal character: " + invalidChar +
                "\nNon terminal symbols must be ascii characters between capital A-Z");
    }
}
