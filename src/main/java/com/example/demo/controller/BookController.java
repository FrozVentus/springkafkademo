package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.dao.Search;
import com.example.demo.service.BookService;

@RestController
public class BookController implements WebMvcConfigurer{
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookService service;
    
    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
    
    @PostMapping("/book")
    public ResponseEntity<?> requestBook(@Valid Search search, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            service.sendQuery(search.getCode());
        }
        else {
            log.info("code: " + search.getCode() + " is invalid.");
            return ResponseEntity.badRequest().body("Invalid Query");
        }
        return ResponseEntity.ok().body("Query sent with value: " + search.getCode());
    }
    
    @GetMapping("/book")
    public ModelAndView loadPage(Search search) {
        return new ModelAndView("bookstore");
    }

    @KafkaListener(topics = "response", groupId = "service")
    public void listenResponse(String message) {
        log.info("Received message: " + message);
    }
    
}
