package se.umu.cs.warn.buster.cyk.topdown;

import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a top down approach.
 */
public class CYKTopDown implements CYKStrategy {

    private Grammar grammar;

    @Override
    public boolean parse(char[] string) {
        return false;
    }

    @Override
    public String getName() {
        return "CYKTopDown";
    }

    @Override
    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }
}
