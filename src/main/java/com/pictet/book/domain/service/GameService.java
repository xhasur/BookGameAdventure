package com.pictet.book.domain.service;

import static com.pictet.book.persistence.mapper.CommonMapper.mapToResponse;
import static com.pictet.book.web.util.Constants.*;

import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.ResponseGameDto;
import com.pictet.book.domain.exception.GameNotFound;
import com.pictet.book.domain.repository.GameRepository;
import com.pictet.book.persistence.entity.*;
import java.util.Optional;
import org.springframework.stereotype.Service;

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

  public GameDto startGame(Long gameRequestId, String playerName) {
    Book book = this.bookService.getBookById(gameRequestId);

    Section beginSection =
        book.getSections().stream()
            .filter(section -> section.getType().equals(SECTION_BEGIN))
            .findFirst()
            .orElseThrow();

    Game game = new Game();
    game.setBook(book);
    game.setPlayerName(playerName);
    game.setHealth(10);
    game.setIdSection(beginSection.getIdSection());
    game.setGameStatus(GAME_STATUS_IN_PROGRESS);

    return gameRepository.saveGame(game);
  }

  public ResponseGameDto findById(long id) {
    Game game = this.gameRepository.getGame(id);
    if (game == null) {
      throw new GameNotFound();
    }
    return mapToResponse(game);
  }

  public ResponseGameDto chooseOptionGame(long gameId, long optionId) {
    Game game = Optional.ofNullable(gameRepository.getGame(gameId))
            .orElseThrow(() -> new RuntimeException("gameId"));

    if (game.getGameStatus().equals(GAME_STATUS_FINISHED)) {
      return mapToResponse(game);
    }

    Section currentSection =
        Optional.ofNullable(
                sectionService.getBookBySectionIdAndBookIdEntity(game.getIdSection(), game.getBook().getId()))
            .orElseThrow(() -> new RuntimeException("game.getIdSection()"));

    Option option =
        currentSection.getOptions().stream()
            .filter(o -> o.getGotoId() == optionId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("optionId"));

    Section nextSection = Optional.ofNullable(
            sectionService.getBookBySectionIdAndBookIdEntity(
                    optionId,
                    game.getBook().getId()
            )
    ).orElseThrow(() -> new RuntimeException("optionId"));


    if (option.getConsequence() != null) {
      Consequence consequence = option.getConsequence();

      int newHealth = game.getHealth();
      switch (consequence.getType()) {
        case LOSE_HEALTH -> newHealth -= consequence.getValue();
        case GAIN_HEALTH -> newHealth += consequence.getValue();
      }
      game.setHealth(newHealth);

      if (newHealth <= 0) {
        game.setGameStatus(GAME_STATUS_DEAD);
      }
    }

    if (nextSection.getType().equals(SECTION_END)) {
      game.setGameStatus(GAME_STATUS_FINISHED);
    }

    if (!GAME_STATUS_DEAD.equals(game.getGameStatus())) {
      game.setIdSection(nextSection.getIdSection());
    }

    gameRepository.saveGame(game);
    return mapToResponse(game);
  }
}
