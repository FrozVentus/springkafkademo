package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.demo.dao.Book;

class BookServiceImplTest {
    
    private BookServiceImpl bookService = new BookServiceImpl();
    
    @Test
    void testConvertBookToJSON() {
        Book bookToConvert = new Book("BookofLight", "01");
        bookToConvert.setId(Long.valueOf(01));
        String converted = "{\r\n  \"id\" : 01,\r\n  \"name\" : \"BookofLight\",\r\n  \"code\" : \"01\"\r\n}";
        assertEquals(converted, bookService.convertBookToJSON(Optional.of(bookToConvert)));
    }

}
