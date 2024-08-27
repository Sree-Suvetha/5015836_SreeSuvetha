package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.model.Book;
import com.example.BookStoreAPI.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = List.of(
        );
        given(bookService.findAllBooks()).willReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].author").value("Author 2"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Book 1", "Author 1");
        given(bookService.findBookById(1L)).willReturn(Optional.of(book));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testGetBookByIdNotFound() throws Exception {
        given(bookService.findBookById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1L, "Book 1", "Author 1");
        given(bookService.saveBook(book)).willReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book existingBook = new Book(1L, "Book 1", "Author 1");
        Book updatedBook = new Book(1L, "Updated Book", "Updated Author");
        given(bookService.findBookById(1L)).willReturn(Optional.of(existingBook));
        given(bookService.saveBook(updatedBook)).willReturn(updatedBook);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testUpdateBookNotFound() throws Exception {
        Book book = new Book(1L, "Updated Book", "Updated Author");
        given(bookService.findBookById(1L)).willReturn(Optional.empty());

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBook() throws Exception {
        given(bookService.findBookById(1L)).willReturn(Optional.of(new Book(1L, "Book 1", "Author 1")));

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).deleteBook(1L);
    }

    @Test
    public void testDeleteBookNotFound() throws Exception {
        given(bookService.findBookById(1L)).willReturn(Optional.empty());

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNotFound());
    }
}
