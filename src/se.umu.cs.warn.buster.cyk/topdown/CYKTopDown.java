package se.umu.cs.warn.buster.cyk.topdown;

import se.umu.cs.warn.buster.cyk.CYKStrategy;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a top down approach.
 */
public class CYKTopDown implements CYKStrategy {
    @Override
    public boolean parse(String string) {
        return false;
    }

    @Override
    public String getName() {
        return "CYKTopDown";
    }
}
