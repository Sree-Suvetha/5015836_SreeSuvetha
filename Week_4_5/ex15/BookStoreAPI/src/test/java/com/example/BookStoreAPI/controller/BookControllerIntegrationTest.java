package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.model.Book;
import com.example.BookStoreAPI.repository.BookRepository;
import com.example.BookStoreAPI.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use H2 for testing
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testGetAllBooks() throws Exception {
        bookRepository.save(new Book(null, "Book 1", "Author 1"));
        bookRepository.save(new Book(null, "Book 2", "Author 2"));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].author").value("Author 2"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = bookRepository.save(new Book(null, "Book 1", "Author 1"));

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testGetBookByIdNotFound() throws Exception {
        mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(null, "Book 1", "Author 1");

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Book 1\", \"author\":\"Author 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book existingBook = bookRepository.save(new Book(null, "Book 1", "Author 1"));
        Book updatedBook = new Book(existingBook.getId(), "Updated Book", "Updated Author");

        mockMvc.perform(put("/books/" + existingBook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\", \"author\":\"Updated Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testUpdateBookNotFound() throws Exception {
        mockMvc.perform(put("/books/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\", \"author\":\"Updated Author\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = bookRepository.save(new Book(null, "Book 1", "Author 1"));

        mockMvc.perform(delete("/books/" + book.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBookNotFound() throws Exception {
        mockMvc.perform(delete("/books/999"))
                .andExpect(status().isNotFound());
    }
}
