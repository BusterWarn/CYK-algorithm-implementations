package se.umu.cs.warn.buster.cyk.topdown;

import org.junit.Test;
import se.umu.cs.warn.buster.cyk.CYKStrategy;
import se.umu.cs.warn.buster.cyk.Grammar;
import se.umu.cs.warn.buster.cyk.naive.CYKNaive;

import static org.junit.Assert.*;

public class CYKTopDownTest {

    private CYKStrategy setStrategyWithSABGrammar() {
        Grammar grammar = new Grammar();
        grammar.setStart('S');
        grammar.setNonTerminals("SAB".toCharArray());
        grammar.setTerminals("ab".toCharArray());
        grammar.addProductionRule('S', "AB");
        grammar.addProductionRule('A', "BA");
        grammar.addProductionRule('A', 'a');
        grammar.addProductionRule('B', "AB");
        grammar.addProductionRule('B', 'b');

        CYKStrategy strategy = new CYKTopDown();
        strategy.setGrammar(grammar);
        return strategy;
    }

    private CYKStrategy setStrategyWithParenthesisGrammar() {
        Grammar grammar = new Grammar();
        grammar.setStart('S');
        grammar.setNonTerminals("SABLR".toCharArray());
        grammar.setTerminals("b()".toCharArray());
        grammar.addProductionRule('S', "SS");
        grammar.addProductionRule('S', "LA");
        grammar.addProductionRule('S', "LR");
        grammar.addProductionRule('S', 'b');
        grammar.addProductionRule('A', "SR");
        grammar.addProductionRule('L', '(');
        grammar.addProductionRule('R', ')');

        CYKStrategy strategy = new CYKTopDown();
        strategy.setGrammar(grammar);
        return strategy;
    }

    @Test
    public void testCorrectAB0() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(true, strategy.parse("ab".toCharArray()));
    }

    @Test
    public void testCorrectAB1() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(true, strategy.parse("abab".toCharArray()));
    }

    @Test
    public void testCorrectAB2() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(true, strategy.parse("ababab".toCharArray()));
    }

    @Test
    public void testFaultyAB0() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(false, strategy.parse("a".toCharArray()));
    }

    @Test
    public void testFaultyAB1() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(false, strategy.parse("b".toCharArray()));
    }

    @Test
    public void testFaultyAB2() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(false, strategy.parse("c".toCharArray()));
    }

    @Test
    public void testFaultyAB3() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(false, strategy.parse("ba".toCharArray()));
    }

    @Test
    public void testFaultyAB4() {
        CYKStrategy strategy = setStrategyWithSABGrammar();
        assertEquals(false, strategy.parse("aba".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis0() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("()".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis1() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(b)".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis2() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(bb)".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis3() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(bbbbbbbbbb)".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis5() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(b)()".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis5_2() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(bbb)(b)".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis6() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(()b())".toCharArray()));
    }

    @Test
    public void testCorrectParenthesis7() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(true, strategy.parse("(bbbb(b)bb(b()b)b)()".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis0() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse(")(".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis1() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse("(bbbbb(".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis2() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse("(bbbb)b)".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis3() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse(")bb)".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis4() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse("(b(bbbbb)".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis5() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse("b(bb(bbb)".toCharArray()));
    }

    @Test
    public void testFaultyParenthesis6() {
        CYKStrategy strategy = setStrategyWithParenthesisGrammar();
        assertEquals(false, strategy.parse("b(bbabbb)".toCharArray()));
    }
}