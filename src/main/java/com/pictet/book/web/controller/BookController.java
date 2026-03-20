package com.pictet.book.web.controller;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/get")
    @Operation(summary = "Get books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public List<BookDto> getBooks() {
        return bookService.getAll();
    }

    @PostMapping("/save")
    @Operation(summary = "Save book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book saved successfully", content = @Content) ,
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<BookDto> add(@RequestBody BookDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.add(book));

    }

}
