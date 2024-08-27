package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.assembler.BookResourceAssembler;
import com.example.BookStoreAPI.model.Book;
import com.example.BookStoreAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookResourceAssembler bookResourceAssembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<EntityModel<Book>> books = bookService.findAllBooks().stream()
                .map(bookResourceAssembler::toModel)
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(bookResourceAssembler::toModel)
                .map(bookResource -> new ResponseEntity<>(bookResource, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Book>> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        EntityModel<Book> bookResource = bookResourceAssembler.toModel(savedBook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        return new ResponseEntity<>(bookResource, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        if (bookService.findBookById(id).isPresent()) {
            updatedBook.setId(id);
            Book book = bookService.saveBook(updatedBook);
            EntityModel<Book> bookResource = bookResourceAssembler.toModel(book);
            return new ResponseEntity<>(bookResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (bookService.findBookById(id).isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
