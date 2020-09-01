package se.umu.cs.warn.buster.cyk;

import se.umu.cs.warn.buster.cyk.naive.CYKNaive;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Main class for running tests on different implementations of CYK algorithm.
 */
public class Main {

    public static void main(String[] args) {

        Grammar grammar = setupGrammar();
        Parser parser = new Parser(grammar);
        parser.setStrategy(new CYKNaive());
        System.out.println("abab belongs to Grammar: " + parser.parse("abab"));
        System.out.println("abbab belongs to Grammar: " + parser.parse("abbab"));
    }

    private static Grammar setupGrammar() {
        Grammar grammar = new Grammar();
        grammar.setNonTerminals("SAB".toCharArray());
        grammar.setTerminals("ab".toCharArray());
        grammar.setStart('S');
        grammar.addProductionRule('S', "AB");
        grammar.addProductionRule('A', "BA");
        grammar.addProductionRule('A', 'a');
        grammar.addProductionRule('B', "AB");
        grammar.addProductionRule('B', 'b');
        return grammar;
    };
}
