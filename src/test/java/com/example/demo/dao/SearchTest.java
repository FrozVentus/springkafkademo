package com.example.demo.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTest {

    @Test
    void testGetCode() {
        String testString = "code";
        Search toTest = new Search("code");
        Assertions.assertEquals(testString, toTest.getCode());
        
        toTest = new Search("wrongCode");
        Assertions.assertNotEquals(testString, toTest.getCode());
    }

    @Test
    void testSetCode() {
        String testString = "code";
        Search toTest = new Search("code");
        Assertions.assertEquals(testString, toTest.getCode());
        // change code
        toTest.setCode("wrongCode");
        Assertions.assertNotEquals(testString, toTest.getCode());
        // change back to original code
        toTest.setCode("code");
        Assertions.assertEquals(testString,  toTest.getCode());
    }

    @Test
    void testEqualsObject() {
        Search first = new Search("code");
        Search second = new Search("wrongCode");
        String third = new String("wrongObject");
        Search fourth = new Search("changeCode");
        fourth.setCode("code");
        
        Assertions.assertNotEquals(first, second);
        Assertions.assertNotEquals(first, third);
        Assertions.assertEquals(first, fourth);
    }

    @Test
    void testToString() {
        String testString = "Search{code='toString'}";
        Search toTest = new Search("toString");
        Assertions.assertEquals(testString, toTest.toString());
    }

}
