package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void checkCalcSumPositiveTesting(){
        Calculator calc = new Calculator();
        assertEquals(3, (int) calc.CalcSum(1, 2));
    }

    @Test
    void checkCalcSumNegativeTesting(){
        Calculator calc = new Calculator();
        assertNotEquals(2, (int) calc.CalcSum(1, -1));
    }

    @Test
    void checkCalcProductPositiveTesting() {
        Calculator calc = new Calculator();
        assertEquals(1, (int) calc.CalcProduct(1, 1));
    }

    @Test
    void checkCalcProductNegativeTesting() {
        Calculator calc = new Calculator();
        assertNotEquals(0, (int) calc.CalcProduct(-1, 1));
    }

    @Test
    void checkCalcDiffPositiveTesting() {
        Calculator calc = new Calculator();
        assertEquals(0, (int) calc.CalcDiff(2, 2));
    }

    @Test
    void checkCalcDiffNegativeTesting() {
        Calculator calc = new Calculator();
        assertNotEquals(0, (int) calc.CalcDiff(-1, 1));
    }

    @Test
    void checkCalcDivisionPositiveTesting() {
        Calculator calc = new Calculator();
        assertEquals(1, (double) calc.CalcDivision(1, 1));
    }

    @Test
    void checkCalcDivisionNegativeTesting() {
        Calculator calc = new Calculator();
        assertNotEquals(0, (double) calc.CalcDivision(1, 0));
    }
}