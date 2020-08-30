package se.umu.cs.warn.buster.cyk;

import org.junit.Assert;
import org.junit.Test;
import se.umu.cs.warn.buster.cyk.Grammar;

import static org.junit.Assert.*;

class GrammarTest {

    private Grammar grammar;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        if (grammar == null)
            grammar = new Grammar();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        grammar = null;
    }

    @Test
    public void testSetStart() {
        grammar.setStart('S');
        Assert.assertEquals('S', grammar.getStart());
    }

    @Test
    public void testSetNonTerminals() {
        grammar.setNonTerminals("SAB".toCharArray());
        Assert.assertArrayEquals("SAB".toCharArray(), grammar.getNonTerminals());
    }

    @Test
    public void testSetTerminals() {
        grammar.setTerminals("ab".toCharArray());
        Assert.assertArrayEquals("ab".toCharArray(), grammar.getTerminals());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyStart()  throws IllegalArgumentException {
        grammar.setStart('s');
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyNonTerminals()  throws IllegalArgumentException {
        grammar.setNonTerminals("sab".toCharArray());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyTerminals()  throws IllegalArgumentException {
        grammar.setTerminals("AB".toCharArray());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetStartWhichDoesNotBelongToNonTerminals()  throws IllegalArgumentException {
        grammar.setTerminals("AB".toCharArray());
        grammar.setStart('S');
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNonTerminalsWhichDoesNotContainStart()  throws IllegalArgumentException {
        grammar.setStart('S');
        grammar.setTerminals("AB".toCharArray());
    }

    @Test
    public void testFindCartesianProductByTerminalChar() {

    }

    @Test
    public void testFindMissingCartesianProductByTerminalChar() {

    }

    @Test
    public void testFindCartesianProductByNonTerminalChar() {

    }

    @Test
    public void testFindMissingCartesianProductByNonTerminalChar() {

    }

    @Test
    public void testFindCartesianProductByNonTerminalString() {

    }

    @Test
    public void testFindMissingCartesianProductByNonTerminalString() {

    }
}