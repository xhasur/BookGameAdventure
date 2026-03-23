package com.pictet.book.domain.exception;

public class BookNotFound extends RuntimeException {
    public BookNotFound() {
        super("Book does not exist");
    }
}
