package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.GameDto;
import com.pictet.book.domain.repository.GameRepository;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.entity.Game;
import com.pictet.book.persistence.entity.Section;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final BookService bookService;
  private final GameRepository gameRepository;

  public GameService(BookService bookService, GameRepository gameRepository) {
    this.bookService = bookService;
    this.gameRepository = gameRepository;
  }

  public GameDto startGame(Long gameRequestId) {
    Book book = this.bookService.getBookById(gameRequestId);
    if (book == null) {
      throw new RuntimeException("Book not found");
    }

    Section begin =
        book.getSections().stream()
            .filter(section -> section.getType().equals("BEGIN"))
            .findFirst()
            .orElseThrow();

    Game game = new Game();
    game.setBook(book);
    game.setHealth(10);
    game.setIdSection(begin.getIdSection());
    game.setGameStatus("IN_PROGRESS");

    gameRepository.saveGame(game);
    return gameRepository.findById(game.getId());
  }
}
