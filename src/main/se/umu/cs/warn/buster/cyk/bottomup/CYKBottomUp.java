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

    @Override
    public boolean parse(char[] string) {

        String[][] mem = new String[string.length][];

        for (int i = 0; i < string.length; i++) {
            mem[i] = new String[string.length - i];
            Arrays.fill(mem[i], "");
        }

        for (int i = 0; i < string.length; i++) {

            if (i == 0) {
                for (int j = 0; j < string.length; j++)
                    mem[i][j] = grammar.findCartesianProductFromProduction(String.valueOf(string[j]));
            } else {
                for (int j = 0; (j + i) < string.length; j++) {
                    for (int k = j; k < (j + i); k++) {
                        String leftSideProduction = mem[(k - j)][j];
                        String rightSideProduction = mem[(j + i - k - 1)][(k + 1)];
                        for (int n = 0; n < leftSideProduction.length(); n++)
                            for (int m = 0; m < rightSideProduction.length(); m++)
                                mem[i][j] = grammar.findCartesianProductFromProduction("" +
                                        leftSideProduction.charAt(n) + rightSideProduction.charAt((m)));
                    }
                }
            }
        }

        if (mem[string.length-1][0].contains(String.valueOf(grammar.getStart())))
            return true;

        return false;
    }

    /*


    @Override
    public boolean parse(char[] string) {

        System.out.println("String length: " + string.length + "\r\n");
        // compute upper bound matrix triangle size
        int size = (int) Math.ceil((Math.pow(string.length, 2) / 2 + 0.5 * string.length));
        mem = new String[string.length][];

        for (int i = 0; i < string.length; i++) {
            mem[i] = new String[string.length - i];
            Arrays.fill(mem[i], "");
        }

        for (int i = 0; i < string.length; i++) {

            if (i == 0) {
                for (int j = 0; j < string.length; j++)
                    mem[i][j] = grammar.findCartesianProductFromProduction(String.valueOf(string[j]));
            } else {

                System.out.println("Check " + (i + 1) + " length: " + (i + 1));
                for (int j = 0; (j + i) < string.length; j++) {
                    System.out.print("Checking: " + j + "-" + (j + i));
                    for (int k = j; k < (j + i); k++) {
                        //System.out.print("[" + k + " " + (k + 1) + "] ");
                        System.out.print(" (");
                        for (int q = j; q < k + 1; q++)
                            System.out.print(q);
                        System.out.print(" ");
                        for (int q = k + 1; q <= (j + i); q++)
                            System.out.print(q);
                        System.out.print(")");
                        //System.out.print("(" + (k + 1 - j) + "," + (j + i - k) + "-");
                        //System.out.print(mem[k-j][j] + "" + mem[k-j][0] + ")");
                        System.out.print("mem["+(k-j)+"]["+j+"] mem["+(j+i-k-1)+"]["+(k+1)+"]");
                        //System.out.print(mem[(k-j)][j] + mem[(j+i-k-1)][(k+1)]);
                        //System.out.print(grammar.findCartesianProductFromProduction(mem[(k-j)][j] + mem[(j+i-k-1)][(k+1)]));
                        String leftSideProduction = mem[(k-j)][j];
                        String rightSideProduction = mem[(j+i-k-1)][(k+1)];
                        for (int n = 0; n < leftSideProduction.length(); n++)
                            for (int m = 0; m < rightSideProduction.length(); m++)
                                mem[i][j] = grammar.findCartesianProductFromProduction("" + leftSideProduction.charAt(n) + rightSideProduction.charAt((m)));
                        //System.out.print(" - ijk: " + i+j+k);
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }

        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++)
                System.out.print("[" + i + "][" + j + "] = " + mem[i][j] + "\t");
            System.out.println();
        }

        if (mem[string.length-1][0].contains(String.valueOf(grammar.getStart())))
            return true;

        return false;
    }
     */

    @Override
    public String getName() {
        return "CYKBottomUp";
    }

    @Override
    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }


}
