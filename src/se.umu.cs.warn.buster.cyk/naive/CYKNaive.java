package se.umu.cs.warn.buster.cyk.naive;

import se.umu.cs.warn.buster.cyk.CYKStrategy;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a naive approach.
 */
public class CYKNaive implements CYKStrategy {
    @Override
    public boolean parse(String string) {
        return false;
    }

    @Override
    public String getName() {
        return "CYKNaive";
    }
}
