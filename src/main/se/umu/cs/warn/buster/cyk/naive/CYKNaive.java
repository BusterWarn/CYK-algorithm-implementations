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
    char[] string;

    @Override
    public boolean parse(char[] string) {

        this.string = string;
        return parseStringRecursively(grammar.getStart(), 0, string.length - 1);
    }

    private boolean parseStringRecursively(char fromNonTerminal, int startPos, int endPos) {

        //System.out.print("\nNonterminal: '" + fromNonTerminal + "' string [" + startPos + " " + endPos + "]: ");
        /*for (int i = startPos; i <= endPos; i++) {
            System.out.print(string[i]);
        }*/
        //System.out.println();
        if (startPos == endPos) {
            Character[] terminalProductions = grammar.getTerminalProductions(fromNonTerminal);
            for (int i = 0; i < terminalProductions.length; i++) {
                //System.out.println("From: " + fromNonTerminal + " Char at string[" + startPos + "]: " + string[startPos] + ". Produced: " + terminalProductions[i]);
                if (string[startPos] == terminalProductions[i]) {
                    //System.out.println("TRUE: " + fromNonTerminal + " -> " + string[startPos]);
                    return true;
                }
            }
        }

        String[] nonTerminalProductions = grammar.getNonTerminalProductions(fromNonTerminal);
        for (String s : nonTerminalProductions) {
            char[] productions = s.toCharArray();
            for (int i = startPos + 1; i <= endPos; i++) {
                //System.out.println();
                //System.out.println("first: [" + startPos + " " + (i - 1) + "]");
                //7System.out.println("second: [" + i + " " + endPos + "]");
                if (parseStringRecursively(productions[0], startPos, i - 1)
                        && parseStringRecursively(productions[1], i, endPos)) {

                    //System.out.println("Returning true from\tNonterminal: '" + fromNonTerminal + "' string [" + startPos + " " + endPos + "]: ");
                    return true;
                }
            }
        }
        //System.out.print("Returning false from\tNonterminal: '" + fromNonTerminal + "' string [" + startPos + " " + endPos + "]: ");
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
