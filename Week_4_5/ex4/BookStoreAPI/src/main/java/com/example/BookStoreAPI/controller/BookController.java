package com.example.BookStoreAPI.controller;


import com.example.BookStoreAPI.model.Book;
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
    public List<Book> getAllBooks() {
        return bookList;
    }

    // POST Request: Create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setId((long) (bookList.size() + 1));
        bookList.add(book);
        return book;
    }

    // PUT Request: Update an existing book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                return book;
            }
        }
        return null;
    }

    // DELETE Request: Delete a book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                bookList.remove(book);
                return "Book deleted successfully";
            }
        }
        return "Book not found";
    }
    
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;  // Returning null if the book is not found (you may want to handle this better)
    }
    
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title, 
                                  @RequestParam(required = false) String author) {
        List<Book> filteredBooks = new ArrayList<>(bookList);

        if (title != null && !title.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getTitle().equalsIgnoreCase(title));
        }

        if (author != null && !author.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getAuthor().equalsIgnoreCase(author));
        }

        return filteredBooks;
    }


}
