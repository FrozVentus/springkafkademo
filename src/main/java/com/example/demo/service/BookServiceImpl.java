package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Book;
import com.example.demo.dao.BookRepository;
import com.example.demo.dao.Search;
import com.example.demo.kafka.KafkaTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookServiceImpl implements BookService{
    
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private KafkaTemplate<String, String> producer;
    
    @Autowired    
    private BookRepository repository;
    
    @Override
    public void sendQuery(Search search) {
        producer.send(KafkaTopic.QUERY.getTopic(), search.getCode());
        log.info("Published Kafka event for topic: " + KafkaTopic.QUERY);
    }
    
    @KafkaListener(topics = "query", groupId = "service")
    public void listenQuery(String message) {
        log.info("Received message: " + message);
        
        String book = findBookByCodeInJSON(message);
        
        producer.send(KafkaTopic.RESPONSE.getTopic(), book.toString());
        log.info("Published Kafka event for topic: " + KafkaTopic.RESPONSE);
       
    }

    @KafkaListener(topics = "response", groupId = "controller")
    public void listenResponse(String message) {
        log.info("Received message: " + message);
    }

    public String findBookByCodeInJSON(String code) {
        Optional<Book> book = findBookByCode(code);
        return convertBookToJSON(book);
    }
           
    public Optional<Book> findBookByCode(String code) {
        return repository.findByCode(code);
    }
    
    public String convertBookToJSON(Optional<Book> book) {
        if (book.isPresent()) {
            try {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book.get());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        else {
            return "Book not Found, No Matching Book";
        }
    }
}
