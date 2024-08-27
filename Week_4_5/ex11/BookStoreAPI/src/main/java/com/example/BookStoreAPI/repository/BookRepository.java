package com.example.BookStoreAPI.repository;

import com.example.BookStoreAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
