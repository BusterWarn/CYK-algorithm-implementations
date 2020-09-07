package se.umu.cs.warn.buster.cyk.naive;

import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a naive approach.
 */
public class CYKNaive implements CYKStrategy {

    private Grammar grammar;
    private char[] string;

    @Override
    public boolean parse(char[] string) {

        this.string = string;
        return parseStringRecursively(grammar.getStart(), 0, string.length - 1);
    }

    private boolean parseStringRecursively(char fromNonTerminal, int startPos, int endPos) {

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
                    if (       parseStringRecursively(productions[0], startPos, i - 1)
                            && parseStringRecursively(productions[1], i, endPos))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "CYKNaive";
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }
}
