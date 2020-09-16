package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookRepository;
import com.example.demo.kafka.KafkaTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookService {
    
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private BookRepository repository;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private KafkaTemplate<String, String> producer;
    
    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }
    
    public Optional<Book> findBookByCode(String code) {
        return repository.findByCode(code);
    }
    
    @KafkaListener(topics = "query", groupId = "service")
    public void listenGroupService(String message) {
        
        log.info("Received message: " + message);
        
        String book = findBookByCodeInJSON(message);
        
        producer.send(KafkaTopic.RESPONSE.getTopic(), book.toString());
        log.info("Published Kafka event for type: " + KafkaTopic.RESPONSE + " with value: " + book.toString());
       
    }
    
    public String findBookByCodeInJSON(String code) {
        Book book = findBookByCode(code).orElse(new Book("No Such Book", "No Such Book"));
        return convertBookToJSON(book);
    }
    
    public String convertBookToJSON(Book book) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
