package com.pictet.book.domain.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound() {
        super("Category does not exist");
    }
}
