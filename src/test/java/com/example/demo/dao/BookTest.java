package com.example.demo.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void testToString() {
        Book first = new Book("title", "code");
        first.setId(Long.valueOf(1));
        
        String testString = "Book[id=1, name='title', code='code']";
        
        Assertions.assertEquals(testString, first.toString());
    }

    @Test
    void testSetId() {
        Book first = new Book("title", "code");
        first.setId(Long.valueOf(1));
        Assertions.assertNotEquals(Long.valueOf(2), first.getId());
        // change id
        first.setId(Long.valueOf(2));
        Assertions.assertEquals(Long.valueOf(2), first.getId());
        // change to original id
        first.setId(Long.valueOf(1));
        Assertions.assertNotEquals(Long.valueOf(2), first.getId());
        Assertions.assertEquals(Long.valueOf(1), first.getId());
        
    }

    @Test
    void testGetId() {
        Book first = new Book("title", "code");
        Long testId = Long.valueOf(1);
        first.setId(testId);
        
        Assertions.assertEquals(testId, first.getId());
    }

    @Test
    void testSetName() {
        Book first = new Book("title", "code");
        first.setId(Long.valueOf(1));
        String correctName = "title";
        String testName = "wrongTitle";
        
        Assertions.assertNotEquals(testName, first.getName());
        // change name
        first.setName(testName);
        Assertions.assertNotEquals(correctName, first.getName());
        Assertions.assertEquals(testName, first.getName());
        
        // change to original name
        first.setName(correctName);
        Assertions.assertNotEquals(testName, first.getName());
        Assertions.assertEquals(correctName, first.getName());
    }

    @Test
    void testGetName() {
        String testName = "title";
        Book toTest = new Book("title", "code");
        Assertions.assertEquals(testName, toTest.getName());
        
        toTest = new Book("wrongTitle", "code");
        Assertions.assertNotEquals(testName, toTest.getName());    
    }

    @Test
    void testSetCode() {
        Book first = new Book("title", "code");
        first.setId(Long.valueOf(1));
        String correctCode = "code";
        String testCode = "wrongCode";
        
        Assertions.assertNotEquals(testCode, first.getCode());
        // change name
        first.setCode(testCode);
        Assertions.assertNotEquals(correctCode, first.getCode());
        Assertions.assertEquals(testCode, first.getCode());
        
        // change to original name
        first.setCode(correctCode);
        Assertions.assertNotEquals(testCode, first.getCode());
        Assertions.assertEquals(correctCode, first.getCode());
    }

    @Test
    void testGetCode() {
        String testCode = "code";
        Book toTest = new Book("title", "code");
        Assertions.assertEquals(testCode, toTest.getCode());
        
        toTest = new Book("title", "wrongCode");
        Assertions.assertNotEquals(testCode, toTest.getCode());
    }

}
