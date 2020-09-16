package se.umu.cs.warn.buster.cyk;

import se.umu.cs.warn.buster.cyk.bottomup.CYKBottomUp;
import se.umu.cs.warn.buster.cyk.naive.CYKNaive;
import se.umu.cs.warn.buster.cyk.topdown.CYKTopDown;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Main class for running tests on different implementations of CYK algorithm.
 */
public class Benchmark {

    public static void main(String[] args) {

        Grammar grammar = createGrammar();
        Parser parser = new Parser(grammar);

        String string = "";
        int nrRuns = 100;

        CYKStrategy[] strategies = new CYKStrategy[3];
        strategies[0] = new CYKNaive();
        strategies[1] = new CYKTopDown();
        strategies[2] = new CYKBottomUp();

        int[][] operations = new int[3][nrRuns];
        long[][] timeStapms = new long[3][nrRuns];



        for (int i = 0; i < nrRuns; i++) {

            string = "(" + string + ")";
            System.out.println("Run nr " + i + " string: '" + string + "'");

            for (int j = 0; j < 3; j++) {
                long timeMillis = System.currentTimeMillis();
                parser.setStrategy(strategies[j]);
                operations[j][i] = parser.parse(string);
                timeStapms[j][i] = System.currentTimeMillis() - timeMillis;
            }

            System.out.printf("Finnished parsing: Naive: %d - %d\tTopDown: %d - %d\tBottomUp: %d - %d\r\n\r\n",
                    operations[0][i], timeStapms[0][i],
                    operations[1][i], timeStapms[1][i],
                    operations[2][i], timeStapms[2][i]);
        }
    }

    private static Grammar createGrammar() {
        Grammar grammar = new Grammar();
        grammar.setStart('S');
        grammar.setNonTerminals("SALR".toCharArray());
        grammar.setTerminals("()".toCharArray());
        grammar.addProductionRule('S', "SS");
        grammar.addProductionRule('S', "LA");
        grammar.addProductionRule('S', "LR");
        grammar.addProductionRule('A', "SR");
        grammar.addProductionRule('L', '(');
        grammar.addProductionRule('R', ')');

        return grammar;
    }
}
