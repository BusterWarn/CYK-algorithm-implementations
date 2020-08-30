package se.umu.cs.warn.buster.cyk.bottomup;

import se.umu.cs.warn.buster.cyk.CYKStrategy;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Implementation of CYK algorithm using a bottom up approach.
 */
public class CYKBottomUp implements CYKStrategy {
    @Override
    public boolean parse(char[] string) {
        return false;
    }

    @Override
    public String getName() {
        return "CYKBottomUp";
    }
}
