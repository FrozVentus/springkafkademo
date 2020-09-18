package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.BookshopApplication;
import com.example.demo.dao.Book;
import com.example.demo.dao.BookRepository;
import com.example.demo.dao.Result;
import com.example.demo.dao.Search;
import com.example.demo.kafka.KafkaTopic;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        return ResponseEntity.ok().body(search);
    }
    
    @GetMapping("/book")
    public ModelAndView loadPage(Search search) {
        return new ModelAndView("bookstore");
    }

}
