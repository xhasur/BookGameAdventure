package com.pictet.book.domain.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

  public CategoryAlreadyExistsException(String title) {
    super("The category already exist");
  }
}
