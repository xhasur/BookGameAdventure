package com.pictet.book.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.ResponseGameDto;
import com.pictet.book.domain.repository.GameRepository;
import com.pictet.book.domain.service.BookService;
import com.pictet.book.domain.service.GameService;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.entity.Game;
import com.pictet.book.persistence.entity.Section;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

  @InjectMocks private GameService gameService;
  @InjectMocks private BookService bookService;

  @Mock private GameRepository gameRepository;

  @Test
  @DisplayName("Test Should work correctly the method findById when the parameters are correct")
  public void testFindById() {

    Game game = new Game();
    game.setId(1L);
    game.setHealth(10);
    game.setPlayerName("Andres");

    when(gameRepository.getGame(1)).thenReturn(game);
    ResponseGameDto response = gameService.findById(1);

    assertEquals(response.getPlayerName(), "Andres");
    assertEquals(response.getHealth(), 10);
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method findById when the method return error o null")
  public void testFindByIdError() {
    when(gameRepository.getGame(1)).thenReturn(null);
    Exception exception = assertThrows(Exception.class, () -> gameService.findById(1));
    assertEquals("Game does not exist", exception.getMessage());
  }

  @Test
  @DisplayName("Test Should work correctly the method startGame when the parameters are correct")
  public void testStartGame() {
    Book book = new Book();
    book.setAuthor("Evelyn Stormrider");
    book.setTitle("The Crystal Caverns");

    Section section = new Section();
    section.setType("BEGIN");
    section.setBook(book);

    book.setSections(List.of(section));

    when(bookService.getBookById(1)).thenReturn(book);
    GameDto response = gameService.startGame(1L, "Andres");

    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals(10, response.getHealth());
    verify(bookService).getBookById(1);
  }
}
