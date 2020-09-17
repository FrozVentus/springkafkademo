package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
import com.example.demo.entity.Book;
import com.example.demo.entity.BookRepository;
import com.example.demo.entity.Result;
import com.example.demo.entity.Search;
import com.example.demo.kafka.KafkaTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BookController implements WebMvcConfigurer{
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookService service;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Result result = new Result();
    private Model model;
    
    @Autowired
    private KafkaTemplate<String, String> producer;
    
    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("bookresult");
    }
    
    @PostMapping("/book")
    public String requestBook(@Valid Search search, BindingResult bindingResult) {
        if (search.getCode() != null && !bindingResult.hasErrors()) {
            producer.send(KafkaTopic.QUERY.getTopic(), search.getCode());
            log.info("Published Kafka event for topic: " + KafkaTopic.QUERY + " with value: " + search.getCode());
        }
        else {
            log.info("code: " + search.getCode() + " is invalid.");
            return "bookstore";
        }
        return "redirect:/result";
    }
    
    @GetMapping("/book")
    public String loadPage(Search search) {
        return "bookstore";
    }

    @KafkaListener(topics = "response", groupId = "service")
    public String listenGroupService(String message) {
        log.info("Received message: " + message);
        return "redirect:/result";
    }
}
