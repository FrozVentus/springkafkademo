package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BookController {
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookService service;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
    
    @GetMapping("book")
    public String findByCode(@RequestParam (value = "code", required = false) String code, Model model) {
        log.info("Searching for book with code: " + code);
        Book result = service.findByCode(code).orElse(new Book("", ""));
        log.info("Returned: " + result.toString());
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
}
