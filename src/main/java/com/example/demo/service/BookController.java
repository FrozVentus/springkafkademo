package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

import com.example.demo.BookshopApplication;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookRepository;
import com.example.demo.kafka.KafkaTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BookController {
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookService service;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    
    @Autowired
    private KafkaTemplate<String, String> producer;
    
    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
    
    @GetMapping("book")
    public String findByCode(@RequestParam (value = "code", required = false) String code, Model model) {
        
        producer.send(KafkaTopic.QUERY.getTopic(), code);
        log.info("Published Kafka event for type: " + KafkaTopic.QUERY + " with value: " + code);
        
        Book result = service.findBookByCode(code).orElse(new Book("", ""));
        
        model.addAttribute("book", result);
        try {
            model.addAttribute("result",
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        model.addAttribute("code", "");
        
        return "bookcontroller";
    }
    
    @KafkaListener(topics = "response", groupId = "service")
    public void listenGroupService(String message) {
        log.info("Received message: " + message);
    }
}
