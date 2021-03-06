package se.umu.cs.warn.buster.cyk;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrammarTest {

    private Grammar grammar;

    private void grammarSetUp() {
        grammar = new Grammar();
        grammar.setStart('S');
        grammar.setNonTerminals("SAB".toCharArray());
        grammar.setTerminals("ab".toCharArray());
    }

    private void setGrammarWithProductions() {
        grammarSetUp();
        grammar.addProductionRule('S', "AB");
        grammar.addProductionRule('A', "BA");
        grammar.addProductionRule('A', 'a');
        grammar.addProductionRule('B', "AB");
        grammar.addProductionRule('B', 'b');
    }

    @Test
    public void testSetStart() {
        grammar = new Grammar();
        grammar.setStart('S');
        Assert.assertEquals('S', grammar.getStart());
    }

    @Test
    public void testSetNonTerminals() {
        grammar = new Grammar();
        grammar.setNonTerminals("SAB".toCharArray());
        Assert.assertArrayEquals("SAB".toCharArray(), grammar.getNonTerminals());
    }

    @Test
    public void testSetTerminals() {
        grammar = new Grammar();
        grammar.setTerminals("ab".toCharArray());
        Assert.assertArrayEquals("ab".toCharArray(), grammar.getTerminals());
    }

    @Test
    public void testSetParenthesisTerminals() {
        grammar = new Grammar();
        grammar.setTerminals("()".toCharArray());
        Assert.assertArrayEquals("()".toCharArray(), grammar.getTerminals());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyStart()  throws IllegalArgumentException {
        grammar = new Grammar();
        grammar.setStart('s');
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyNonTerminals()  throws IllegalArgumentException {
        grammar = new Grammar();
        grammar.setNonTerminals("sab".toCharArray());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFaultyTerminals()  throws IllegalArgumentException {
        grammar = new Grammar();
        grammar.setTerminals("AB".toCharArray());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetStartWhichDoesNotBelongToNonTerminals()  throws IllegalArgumentException {
        grammar = new Grammar();
        grammar.setTerminals("AB".toCharArray());
        grammar.setStart('S');
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNonTerminalsWhichDoesNotContainStart()  throws IllegalArgumentException {
        grammar = new Grammar();
        grammar.setStart('S');
        grammar.setTerminals("AB".toCharArray());
    }

    @Test
    public void testAddNonTerminalProduction() {
        grammarSetUp();
        grammar.addProductionRule('S', "AB");
        assertEquals("AB", grammar.getNonTerminalProductions('S')[0]);
    }

    @Test
    public void testAddTerminalProduction() {
        grammarSetUp();
        grammar.addProductionRule('A', 'a');
        char production = grammar.getTerminalProductions('A')[0];
        assertEquals('a', production);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNonTerminalProductionWithFaultNonExistingNonFromTerminal() {
        grammarSetUp();
        grammar.addProductionRule('C', "AB");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNonTerminalProductionWithFaultNonExistingToNonTerminal() {
        grammarSetUp();
        grammar.addProductionRule('A', "AC");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNonTerminalProductionWithFaultNotInCNF1() {
        grammarSetUp();
        grammar.addProductionRule('A', "A");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNonTerminalProductionWithFaultNotInCNF2() {
        grammarSetUp();
        grammar.addProductionRule('A', "ABA");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddTerminalProductionWithFaultNonExistingNonTerminal() {
        grammarSetUp();
        grammar.addProductionRule('C', 'a');
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNonTerminalProductionWithFaultNonExistingTerminal() {
        grammarSetUp();
        grammar.addProductionRule('A', 'c');
    }

    @Test
    public void testFindCartesianProduct0() {
        setGrammarWithProductions();
        assertEquals("A", grammar.findCartesianProductFromProduction("a"));
    }

    @Test
    public void testFindCartesianProduct1() {
        setGrammarWithProductions();
        assertEquals("B", grammar.findCartesianProductFromProduction("b"));
    }

    @Test
    public void testFindCartesianProduct2() {
        setGrammarWithProductions();
        String cartesianProduct = grammar.findCartesianProductFromProduction("AB");
        assertTrue(cartesianProduct.contains("S"));
    }

    @Test
    public void testFindCartesianProduct3() {
        setGrammarWithProductions();
        String cartesianProduct = grammar.findCartesianProductFromProduction("AB");
        assertTrue(cartesianProduct.contains("B"));
    }

    @Test
    public void testFindCartesianProduct4() {
        setGrammarWithProductions();
        String cartesianProduct = grammar.findCartesianProductFromProduction("AB");
        assertEquals(2, cartesianProduct.length());
    }

    @Test
    public void testFindCartesianProduct5() {
        setGrammarWithProductions();
        String cartesianProduct = grammar.findCartesianProductFromProduction("BA");
        assertEquals("A", cartesianProduct);
    }
}