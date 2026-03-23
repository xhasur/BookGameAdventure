package com.pictet.book.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.exception.BookNotFound;
import com.pictet.book.domain.service.BookService;
import com.pictet.book.web.controller.BookController;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private BookService bookService;

    @Test
    @DisplayName("Test - Should return a Book object when the parameters sent are correct.")
    void testGetBooks_Found() throws Exception {
        String title = "The Crystal Caverns";
        String author = "Evelyn Stormrider";
        String difficulty = "EASY";

        BookDto book1 = new BookDto();
        List<BookDto> mockBooks = new ArrayList<>();
        mockBooks.add(book1);

        when(bookService.getBooksByConditions(title, author, difficulty, null))
                .thenReturn(mockBooks);

        mockMvc.perform(get("/books")
                        .param("title", title)
                        .param("author", author)
                        .param("difficulty", difficulty)
                        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test - Should return badRequest when the endpoint is called without values")
    void testGetBooks_empty() throws Exception {
        String title = "The Crystal Caverns";
        String author = "Evelyn Stormrider";
        String difficulty = "HARD";

        when(bookService.getBooksByConditions(title, author, difficulty, null))
                .thenReturn(List.of());

        mockMvc.perform(get("/books")
                        .param("title", title)
                        .param("author", author)
                        .param("difficulty", difficulty)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test - Should return a Book object byId.")
    void testGetBooks_Found_byId() throws Exception {
        long bookId = 1;

        BookDto book1 = new BookDto();
        when(bookService.findById(bookId))
                .thenReturn(book1);

        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test - Should return a not found when the id is not correct.")
    void test_GetBooks_not_found_byId() throws Exception {
        long bookId = 1;

        when(bookService.findById(bookId))
                .thenThrow(BookNotFound.class);

        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Test - Should return a not found when the books  is not saved.")
    void test_saveBook_success() throws Exception {
        String title = "The Crystal Caverns";
        String author = "Evelyn Stormrider";
        String difficulty = "EASY";

        BookDto book1 = new BookDto();
        book1.setTitle(title);
        book1.setAuthor(author);
        book1.setDifficulty(difficulty);

        when(bookService.add(book1)).thenReturn(null);

        mockMvc.perform(post("/books/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isNotFound());
    }
}


