package com.pictet.book.web.controller;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    @Operation(summary = "Get ....")
    public List<BookDto> getBooks() {
        return bookService.getAll();

    }

}
