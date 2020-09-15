package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookRepository;

@Service
public class BookService {
    private BookRepository repository;
    
    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }
    
    public Optional<Book> findByCode(String code) {
        return repository.findByCode(code);
    }
}
