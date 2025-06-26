package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.security.JwtFilter;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = BookController.class,
excludeAutoConfiguration = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
    org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
}
)



public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private JwtFilter jwtFilter;  // mock to prevent filter from blocking

    @MockBean
    private JwtUtil jwtUtil;      // mock to resolve dependency

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book("111", "Book A", "Author A", 2020);
        Book book2 = new Book("222", "Book B", "Author B", 2021);

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].isbn").value("111"))
                .andExpect(jsonPath("$[1].isbn").value("222"));
    }

    @Test
    public void testGetBookByIsbn() throws Exception {
        Book book = new Book("123", "Test Book", "Test Author", 2022);

        when(bookService.getBookByIsbn("123")).thenReturn(book);

        mockMvc.perform(get("/api/books/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("123"))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.publicationYear").value(2022));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book("456", "New Book", "Author X", 2023);

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("456"))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("Author X"))
                .andExpect(jsonPath("$.publicationYear").value(2023));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book("789", "Updated Book", "Author Y", 2024);

        when(bookService.updateBook(eq("789"), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/789")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("789"))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Author Y"))
                .andExpect(jsonPath("$.publicationYear").value(2024));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook("999");

        mockMvc.perform(delete("/api/books/999"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book with ISBN 999 deleted successfully."));
    }
}
