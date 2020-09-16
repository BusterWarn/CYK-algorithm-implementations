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

        boolean runNaive = false;

        Grammar grammar = createGrammar();
        Parser parser = new Parser(grammar);

        String string = "";
        int nrRuns = 100;

        CYKStrategy[] strategies = new CYKStrategy[3];
        strategies[0] = new CYKNaive();
        strategies[1] = new CYKTopDown();
        strategies[2] = new CYKBottomUp();

        long[][] operations = new long[3][nrRuns];
        long[][] timeStapms = new long[3][nrRuns];


        System.out.println("Results printed to csv format. Format is the following:\n" +
                "length of string, naive nr operations, naive time, topdown nr operations, topdown time, " +
                "bottom up nr of operations, bottom up time");
        int three_counter = 1;
        for (int i = 0; i < nrRuns; i++) {

            int halfIndex = string.length() / 2;

            if (three_counter == 1) {
                string = string.substring(0,halfIndex) + "b" + string.substring(halfIndex, string.length());
                three_counter++;
            }
            else if (three_counter == 2) {
                if ((i + 1) % 4 == 0) {
                    string = "b" + string;
                } else {
                    string = string + "b";
                }
                three_counter++;
            } else if (three_counter == 3) {

                // remove one b from string
                for (int k = halfIndex; k < string.length(); k++) {
                    if (string.charAt(k) == 'b') {
                        string = string.substring(0, k) + string.substring(k + 1);
                        break;
                    }
                }
                string = "(" + string + ")";
                three_counter = 1;
            }

            for (int j = 0; j < 3; j++) {
                if (!runNaive && (j == 0))
                    continue;
                long timeMillis = System.currentTimeMillis();
                parser.setStrategy(strategies[j]);
                operations[j][i] = parser.parse(string);
                timeStapms[j][i] = System.currentTimeMillis() - timeMillis;
            }

            System.out.printf("%d,%d,%d,%d,%d,%d,%d\r\n", string.length(),
                    operations[0][i], timeStapms[0][i],
                    operations[1][i], timeStapms[1][i],
                    operations[2][i], timeStapms[2][i]);
        }
    }

    private static Grammar createGrammar() {
        Grammar grammar = new Grammar();
        grammar.setStart('S');
        grammar.setNonTerminals("SALR".toCharArray());
        grammar.setTerminals("b()".toCharArray());
        grammar.addProductionRule('S', "SS");
        grammar.addProductionRule('S', 'b');
        grammar.addProductionRule('S', "LA");
        grammar.addProductionRule('S', "LR");
        grammar.addProductionRule('A', "SR");
        grammar.addProductionRule('L', '(');
        grammar.addProductionRule('R', ')');

        return grammar;
    }
}
