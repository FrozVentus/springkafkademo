package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Book;
import com.example.demo.dao.BookRepository;

@SpringBootApplication
@RestController
public class BookshopApplication {

    private static final Logger log = LoggerFactory.getLogger(BookshopApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	    return (args) -> {
	        // book populate
	        log.info("Preloading data");
	        repository.save(new Book("BookofLight", "Light"));
	        repository.save(new Book("BookofDarkness", "Darkness"));
	        repository.save(new Book("BookofFire", "Fire"));
	        repository.save(new Book("BookofWater", "Water"));
	        repository.save(new Book("BookofWind", "Wind"));
	        repository.save(new Book("BookofEarth", "Earth"));
	        log.info("Preloading completed");
	        // book fetch
            /*
             * log.info("Books found with findAll():");
             * log.info("---------------------------"); for (Book book :
             * repository.findAll()) { log.info(book.toString()); } log.info("");
             */
	    };
	}
}
