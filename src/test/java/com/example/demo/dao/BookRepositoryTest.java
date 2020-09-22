package com.example.demo.dao;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;
    
    @Test
    void testFindByCode() {
        String code = "01";
        Book correctBook = new Book("BookofLight", code);
        
        // book exist
        Optional<Book> toTest = repository.findByCode(code);
                
        Assertions.assertThat(toTest.isPresent());
        Assertions.assertThat(toTest.get()).isEqualTo(correctBook);
        
        // book not exist
        code = "00";
        toTest = repository.findByCode(code);
        
        Assertions.assertThat(!toTest.isPresent());
    }

}
