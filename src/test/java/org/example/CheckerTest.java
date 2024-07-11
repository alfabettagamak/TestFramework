package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

    @Test
    void checkIsNumberNegativeTesting(){
        Checker checker = new Checker();
        assertFalse(checker.isNumber("rerewr"));
    }

    @Test
    void checkIsNumberPositiveTesting(){
        Checker checker = new Checker();
        assertTrue(checker.isNumber("55"));
    }

    @Test
    void checkIsEmptyPositiveTesting(){
        Checker checker = new Checker();
        assertTrue(checker.isEmpty(""));
    }

    @Test
    void checkIsEmptyNegativeTesting(){
        Checker checker = new Checker();
        assertFalse(checker.isEmpty("fdgdfgdfg"));
    }

    @Test
    void checkIsCorrectLengthNegativeTesting(){
        Checker checker = new Checker();
        assertFalse(checker.isCorrectLength("555555555"));
    }

    @Test
    void checkIsCorrectLengthPositiveTesting(){
        Checker checker = new Checker();
        assertTrue(checker.isCorrectLength("123"));
    }

    @Test
    void  checkIsZeroDivisionPositiveTesting() {
        Checker checker = new Checker();
        assertTrue(checker.isZeroDivision("0"));
    }

    @Test
    void  checkIsZeroDivisionNegativeTesting() {
        Checker checker = new Checker();
        assertFalse(checker.isZeroDivision("1"));
    }

    @Test
    void checkIsOneOfPositiveTesting() {
        Checker checker = new Checker();
        assertTrue(checker.isOneOf("+"));
    }

    @Test
    void checkIsOneOfNegativeTesting() {
        Checker checker = new Checker();
        assertFalse(checker.isOneOf("%"));
    }

    @Test
    void CheckIsDivisionPositiveTesting() {
        Checker checker = new Checker();
        assertTrue(checker.isDivision("/"));
    }

    @Test
    void CheckIsDivisionNegativeTesting() {
        Checker checker = new Checker();
        assertFalse(checker.isDivision("*"));
    }
}
