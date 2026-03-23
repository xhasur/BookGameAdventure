package com.pictet.book.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.GameRequest;
import com.pictet.book.domain.dto.game.ResponseGameDto;
import com.pictet.book.domain.exception.GameNotFound;
import com.pictet.book.domain.service.GameService;
import com.pictet.book.web.controller.GameController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GameController.class)
class GameControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @MockitoBean
  private GameService gameService;

  @Test
  @DisplayName("Test - Should return a Game object when the parameters are correct.")
  void testGetBooksFound() throws Exception {
    long gameId = 9;
    ResponseGameDto game = new ResponseGameDto();

    when(gameService.findById(gameId)).thenReturn(game);

    mockMvc.perform(get("/game/{id}", gameId)).andExpect(status().isOk());
  }

  @Test
  @DisplayName("Test - Should return a not found when the parameters are incorrect.")
  void testGetBooksNotFound() throws Exception {
    long gameId = 9;

    when(gameService.findById(gameId)).thenThrow(GameNotFound.class);

    mockMvc.perform(get("/game/{id}", gameId)).andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Test - Should return a ok   when the game  is saved.")
  void testStartGame() throws Exception {
   long bookId = 9;
   String playerName = "Andres";

    GameRequest gameRequest = new GameRequest();
    gameRequest.setBookId(bookId);
    gameRequest.setPlayerId(playerName);

    GameDto game = new GameDto();

    when(gameService.startGame(bookId , playerName)).thenReturn(game);

    mockMvc.perform(post("/game/start")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(gameRequest)))
            .andExpect(status().isOk());
  }


  @Test
  @DisplayName("Test - Should return a error   when the game  is not saved.")
  void testStartGameNotWorking() throws Exception {
    long bookId = 9;
    String playerName = "Andres";

    GameRequest gameRequest = new GameRequest();
    gameRequest.setBookId(bookId);
    gameRequest.setPlayerId(playerName);

    GameDto game = new GameDto();

    when(gameService.startGame(bookId , playerName)).thenReturn(game);

    mockMvc.perform(post("/game/start")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }
}
