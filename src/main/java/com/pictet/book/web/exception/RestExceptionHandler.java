package com.pictet.book.web.exception;

import com.pictet.book.domain.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(BookAlreadyExistsException.class)
  public ResponseEntity<Error> handleExceptionBookAlreadyExist(Exception ex) {
    Error error = new Error("book-already-exist", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(BookNotFound.class)
  public ResponseEntity<Error> handleExceptionBookNotFound(Exception ex) {
    Error error = new Error("book-not-found", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(CategoryNotFound.class)
  public ResponseEntity<Error> handleExceptionCategoryNotFound(Exception ex) {
    Error error = new Error("category-not-found", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(CategoryAlreadyExistsException.class)
  public ResponseEntity<Error> handleExceptionCategoryAlreadyExist(Exception ex) {
    Error error = new Error("category-already-exist", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(GameNotFound.class)
  public ResponseEntity<Error> handleExceptionGameNotFound(Exception ex) {
    Error error = new Error("game-not-found", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(BookError.class)
  public ResponseEntity<Error> handleExceptionBookError(Exception ex) {
    Error error = new Error("Book-error", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }
}
