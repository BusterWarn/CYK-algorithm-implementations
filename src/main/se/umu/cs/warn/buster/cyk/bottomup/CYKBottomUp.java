package se.umu.cs.warn.buster.cyk.bottomup;

import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;

import java.util.Arrays;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a bottom up approach.
 */
public class CYKBottomUp implements CYKStrategy {

    private Grammar grammar;
    private int nrOperations;

    @Override
    public boolean parse(char[] string) {

        nrOperations = 0;
        String[][] mem = new String[string.length][];

        for (int i = 0; i < string.length; i++) {
            mem[i] = new String[string.length - i];
            Arrays.fill(mem[i], "");
        }

        // This loops over the rows in the table.
        for (int i = 0; i < string.length; i++) {

            if (i == 0) {
                for (int j = 0; j < string.length; j++) {
                    mem[i][j] = grammar.findCartesianProductFromProduction(String.valueOf(string[j]));
                    nrOperations++;
                }
            } else {

                // This loops over the columns in the table.
                for (int j = 0; (j + i) < string.length; j++) {

                    // This loop tries all combinations of length i. I.e if i=4, ababab => abab,a and a,abab
                    for (int k = j; k < (j + i); k++) {
                        nrOperations++;
                        String leftSideProduction = mem[(k - j)][j];
                        String rightSideProduction = mem[(j + i - k - 1)][(k + 1)];

                        // These loops over all possible combinations of the string at location
                        for (int n = 0; n < leftSideProduction.length(); n++)
                            for (int m = 0; m < rightSideProduction.length(); m++)
                                mem[i][j] = grammar.findCartesianProductFromProduction("" +
                                        leftSideProduction.charAt(n) + rightSideProduction.charAt((m)));
                    }
                }
            }
        }

        return mem[string.length - 1][0].contains(String.valueOf(grammar.getStart()));
    }

    @Override
    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    @Override
    public int getNrOperations() {
        return 0;
    }


}
