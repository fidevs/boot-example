package com.example.repository;

import com.example.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository  extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String Title);
}
