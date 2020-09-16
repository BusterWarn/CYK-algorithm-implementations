package se.umu.cs.warn.buster.cyk.topdown;

import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;

import java.util.HashMap;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a top down approach.
 */
public class CYKTopDown implements CYKStrategy {

    private Grammar grammar;
    private HashMap<String, Boolean> mem;
    private int nrOperations;

    private char[] string;

    @Override
    public boolean parse(char[] string) {

        nrOperations = 0;
        this.string = string;
        mem = new HashMap<>();
        return parseStringRecursively(grammar.getStart(), 0, string.length - 1);
    }

    private boolean parseStringRecursively(char fromNonTerminal, int startPos, int endPos) {

        Boolean solution = mem.get(fromNonTerminal + "" + startPos + "" + endPos);
        if (solution != null)
            return solution;

        nrOperations++;
        if (startPos == endPos) {
            char[] terminalProductions = grammar.getTerminalProductions(fromNonTerminal);

            if (terminalProductions == null)
                return false;
            for (int i = 0; i < terminalProductions.length; i++)
                if (string[startPos] == terminalProductions[i])
                    return true;
        }

        String[] nonTerminalProductions = grammar.getNonTerminalProductions(fromNonTerminal);
        if (nonTerminalProductions != null) {
            for (String s : nonTerminalProductions) {
                char[] productions = s.toCharArray();
                for (int i = startPos + 1; i <= endPos; i++) {

                    boolean leftValue = parseStringRecursively(productions[0], startPos, i - 1);
                    String leftString = String.valueOf(productions[0]) + startPos + (i - 1);
                    if (!mem.containsKey(leftString))
                        mem.put(leftString, leftValue);

                    if (!leftValue)
                        continue;

                    boolean rightValue = parseStringRecursively(productions[1], i, endPos);
                    String rightString = String.valueOf(productions[1]) + i + endPos;
                    if (!mem.containsKey(rightString))
                        mem.put(rightString, rightValue);

                    if (rightValue)
                        return true;
                }
            }
        }
        return false;
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    @Override
    public int getNrOperations() {
        return nrOperations;
    }
}
