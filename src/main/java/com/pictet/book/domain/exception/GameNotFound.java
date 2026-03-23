package com.pictet.book.domain.exception;

public class GameNotFound extends RuntimeException {
  public GameNotFound() {
    super("Game does not exist");
  }
}
