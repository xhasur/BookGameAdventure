package com.pictet.book.web.controller;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.dto.CategoryRequest;
import com.pictet.book.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LogManager.getLogger(BookController.class);
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping("/")
    @Operation(summary = "Get book by title , author, difficulty or  category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found books", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<List<BookDto>> getBooks(@Parameter(description = "title", example = "The Crystal Caverns")
                                                  @RequestParam(value = "title", required = false) String title,
                                                  @Parameter(description = "author", example = "Evelyn Stormrider")
                                                  @RequestParam(value = "author", required = false) String author,
                                                  @Parameter(description = "difficulty", example = "EASY")
                                                  @RequestParam(required = false) String difficulty,
                                                  @Parameter(description = "category")
                                                  @RequestParam(value = "category", required = false) String category) {
    LOGGER.info(
        "BookController::getBooks title: {} , author:{} , difficulty:{} , category:{}   ",
        title,
        author,
        category,
        difficulty);
    List<BookDto> books = bookService.getBooksByConditions(title, author, difficulty, category);
    if (books.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found books", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<BookDto> getBooksById(@Parameter(description = "id", example = "1")
                                                @PathVariable(value = "id") long id) {
    LOGGER.info("BookController::getBooksById id: {} ", id);
    BookDto bookDto = bookService.findById(id);
    if (bookDto == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(bookDto, HttpStatus.OK);
  }

    @PostMapping("/{id}/categories")
    @Operation(summary = "Add category to book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added the category to the book", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<BookDto> addCategory(@Parameter(description = "id", example = "1")
                                               @PathVariable(value = "id") long id,
                                               @Parameter(description = "category", example = "mystery")
                                               @RequestBody CategoryRequest categoryRequest) {
    LOGGER.info("BookController::addCategory id: {} , category: {} ", id, categoryRequest);
    BookDto bookDto = bookService.addCategory(id, categoryRequest);
    if (bookDto == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(bookDto, HttpStatus.OK);
  }

    @DeleteMapping("/{id}/categories/{category}")
    @Operation(summary = "Delete category to book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the category to the book", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<BookDto> deleteCategory(@Parameter(description = "id", example = "1")
                                                  @PathVariable(value = "id") long id,
                                                  @Parameter(description = "category", example = "mystery")
                                                  @PathVariable String category) {
        LOGGER.info("BookController::deleteCategory, id: {}, category: {} ", id, category);
        BookDto bookDto = bookService.deleteCategory(id, category);
        if (bookDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }


    @PostMapping("/save")
    @Operation(summary = "Save book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book saved successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<BookDto> add(@RequestBody BookDto book) {
    LOGGER.info("BookController::add book: {} ", book);
    BookDto bookDto = this.bookService.add(book);
    if (bookDto == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
  }
}
