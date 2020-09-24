package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.Search;
import com.example.demo.service.BookService;

@RestController
public class BookController implements WebMvcConfigurer{
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private BookService service;
    
    @PostMapping("/books")
    public ResponseEntity<?> requestBook(@Valid Search search, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            service.sendQuery(search);
        }
        else {
            log.info("code: " + search.getCode() + " is invalid.");
            return ResponseEntity.badRequest().body("ISBN can only contain 20 numbers");
        }
        return ResponseEntity.ok().body("Query sent with value: " + search.getCode());
    }
    
    @GetMapping("/books")
    public ModelAndView loadPage(Search search) {
        return new ModelAndView("bookstore");
    }
    
    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/books");
    }
    
}
