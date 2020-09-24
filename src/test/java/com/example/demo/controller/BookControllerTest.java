package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dao.BookRepository;
import com.example.demo.service.BookService;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;
    
    @MockBean
    private BookRepository bookRepository;
    
    @Test
    void testRequestBook() throws Exception {
        this.mockMvc.perform(post("/books").param("code", "01")
                                           .contentType("text/plain;charset=UTF-8"))
                    .andExpect(status().isOk());
        this.mockMvc.perform(post("/books").param("code", "AA")
                                           .contentType("text/plain;charset=UTF-8"))
                    .andExpect(status().isBadRequest());
    }

    @Test
    void testLoadPage() throws Exception {
        this.mockMvc.perform(get("/books")).andExpect(status().isOk());
    }
    
    @Test
    void testRedirect() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
    }

}
