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

}