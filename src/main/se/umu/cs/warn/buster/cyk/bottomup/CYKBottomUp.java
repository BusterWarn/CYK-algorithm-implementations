package se.umu.cs.warn.buster.cyk.bottomup;

import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a bottom up approach.
 */
public class CYKBottomUp implements CYKStrategy {

    private Grammar grammar;
    private String[] mem;

    @Override
    public boolean parse(char[] string) {

        // compute upper bound matrix triangle size
        int size = (int) Math.ceil((Math.pow(string.length, 2) / 2 + 0.5 * string.length));
        mem = new String[size];
        return false;
    }

    private boolean parseStringRecursively(char[] string) {
        return false;
    }

    @Override
    public String getName() {
        return "CYKBottomUp";
    }

    @Override
    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    private int computeUpperBoundMatrixSize(int n) {
        return 2;
    }
}
