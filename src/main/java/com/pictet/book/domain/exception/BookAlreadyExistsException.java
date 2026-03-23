package com.pictet.book.domain.exception;

public class BookAlreadyExistsException extends RuntimeException {

  public BookAlreadyExistsException(String title) {
    super("The Book already exist");
  }
}
