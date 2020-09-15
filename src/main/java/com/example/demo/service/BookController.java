package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookRepository;

@RestController
public class BookController {

    private final BookRepository repository;
    
    BookController(BookRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/book/{code}")
    Book findByCode(@PathVariable String code) {
        return repository.findByCode(code).orElse(new Book("No Such Book", "No Such Book"));
    }
}
