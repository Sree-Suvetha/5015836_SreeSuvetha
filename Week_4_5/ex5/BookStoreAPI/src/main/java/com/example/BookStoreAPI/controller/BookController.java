package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // Constructor to add some dummy data for testing
    public BookController() {
        bookList.add(new Book(1L, "Spring Boot Guide", "John Doe", 39.99, "123456789"));
        bookList.add(new Book(2L, "REST API Design", "Jane Smith", 29.99, "987654321"));
    }

    // GET Request: Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    // GET Request: Retrieve a book by ID (Path Variable)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST Request: Create a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.setId((long) (bookList.size() + 1));
        bookList.add(book);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    // PUT Request: Update an existing book
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE Request: Delete a book
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                bookList.remove(book);
                return new ResponseEntity<>("Book deleted successfully", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET Request: Search for books by title and/or author (Query Parameters)
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title, 
                                                   @RequestParam(required = false) String author) {
        List<Book> filteredBooks = new ArrayList<>(bookList);

        if (title != null && !title.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getTitle().equalsIgnoreCase(title));
        }

        if (author != null && !author.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getAuthor().equalsIgnoreCase(author));
        }

        return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
    }
}
