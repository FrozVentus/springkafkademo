package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long>{
    Optional<Book> findByCode(@Param("code") String code);
}
