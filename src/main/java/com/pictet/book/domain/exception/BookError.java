package com.pictet.book.domain.exception;

public class BookError extends RuntimeException {
    public BookError(String error) {
    super("Book contains errors" + error);
    }
}
