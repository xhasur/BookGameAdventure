package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.Game;
import com.pictet.book.domain.repository.GameRepository;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.entity.Section;
import org.springframework.stereotype.Service;

import static com.pictet.book.persistence.mapper.CommonMapper.mapToResponse;

@Service
public class GameService {

  private final BookService bookService;
  private final SectionService sectionService;
  private final GameRepository gameRepository;

  public GameService(
      BookService bookService, SectionService sectionService, GameRepository gameRepository) {
    this.bookService = bookService;
    this.sectionService = sectionService;
    this.gameRepository = gameRepository;
  }

  public GameDto startGame(Long gameRequestId, String playerId) {
    Book book = this.bookService.getBookById(gameRequestId);
    if (book == null) {
      throw new RuntimeException("Book not found");
    }

    Section beginSection =
        book.getSections().stream()
            .filter(section -> section.getType().equals("BEGIN"))
            .findFirst()
            .orElseThrow();

    com.pictet.book.persistence.entity.Game game = new com.pictet.book.persistence.entity.Game();
    game.setBook(book);
    game.setHealth(10);
    game.setIdSection(beginSection.getIdSection());
    game.setGameStatus("IN_PROGRESS");

    gameRepository.saveGame(game);
    return gameRepository.findById(game.getId());
  }

  public Game findById(long id) {
    com.pictet.book.persistence.entity.Game game = this.gameRepository.getGame(id);

    if (game == null) {
      throw new RuntimeException("Game not found");
    }

    Section section = sectionService.getBySectionId(game.getIdSection());
    return mapToResponse(game, section);
  }
}
