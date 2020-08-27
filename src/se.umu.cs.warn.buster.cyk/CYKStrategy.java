package se.umu.cs.warn.buster.cyk;

/**
 * Author: Buster Hultgren Wärn - busterw@cs.umu.se
 *
 * Interface of the Strategy design pattern.
 *
 * This Strategy represents the CYK algorithm and will parse strings to see if they belong to context-free grammars
 * given in Chomsky normal form (CNF).
 */
public interface CYKStrategy {

    /**
     * Parses the string and via the implemented strategy tells if it belongs to a context-free grammar in CNF form.
     * @param string
     * @return
     */
    public boolean parse(String string);

    public String getName();
}
