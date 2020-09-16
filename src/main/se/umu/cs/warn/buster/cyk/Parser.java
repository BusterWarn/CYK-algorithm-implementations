package se.umu.cs.warn.buster.cyk;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Parser for parsing strings and telling if they belong to context-free grammars
 * given in Chomsky normal form (CNF).
 *
 * This parser uses the Strategy design pattern, each implemented parser being able to parse strings.
 * @see se.umu.cs.warn.buster.cyk.CYKStrategy
 */
public class Parser {

    private Grammar grammar;
    private CYKStrategy strategy;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
    }

    public int parse(String string) {

        strategy.setGrammar(grammar);
        if (!strategy.parse(string.toCharArray()))
            return -1;
        return strategy.getNrOperations();
    }

    public void setStrategy(CYKStrategy strategy) {
        this.strategy = strategy;
    }
}
