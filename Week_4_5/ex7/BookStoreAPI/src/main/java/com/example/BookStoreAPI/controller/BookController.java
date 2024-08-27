package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.dto.BookDTO;
import com.example.BookStoreAPI.mapper.BookMapper;
import com.example.BookStoreAPI.model.Book;
import com.example.BookStoreAPI.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOs.add(BookMapper.INSTANCE.bookToBookDTO(book));
        }
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }

    // GET Request: Retrieve a book by ID (Path Variable)
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
                return new ResponseEntity<>(bookDTO, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("Book not found with ID: " + id);
    }

    // POST Request: Create a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        book.setId((long) (bookList.size() + 1));
        bookList.add(book);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");
        
        return new ResponseEntity<>(bookDTO, headers, HttpStatus.CREATED);
    }

    // PUT Request: Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(bookDTO.getTitle());
                book.setAuthor(bookDTO.getAuthor());
                book.setPrice(bookDTO.getPrice());
                book.setIsbn(bookDTO.getIsbn());
                
                BookDTO updatedBookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
                return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("Book not found with ID: " + id);
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
        throw new ResourceNotFoundException("Book not found with ID: " + id);
    }

    // GET Request: Search for books by title and/or author (Query Parameters)
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(required = false) String title, 
                                                      @RequestParam(required = false) String author) {
        List<Book> filteredBooks = new ArrayList<>(bookList);

        if (title != null && !title.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getTitle().equalsIgnoreCase(title));
        }

        if (author != null && !author.isEmpty()) {
            filteredBooks.removeIf(book -> !book.getAuthor().equalsIgnoreCase(author));
        }

        List<BookDTO> filteredBookDTOs = new ArrayList<>();
        for (Book book : filteredBooks) {
            filteredBookDTOs.add(BookMapper.INSTANCE.bookToBookDTO(book));
        }

        return new ResponseEntity<>(filteredBookDTOs, HttpStatus.OK);
    }
}
