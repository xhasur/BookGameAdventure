package com.pictet.book.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.GameRequest;
import com.pictet.book.domain.dto.game.ResponseGameDto;
import com.pictet.book.domain.exception.BookError;
import com.pictet.book.domain.exception.GameNotFound;
import com.pictet.book.domain.service.GameService;
import com.pictet.book.domain.service.SectionService;
import com.pictet.book.web.controller.GameController;
import com.pictet.book.web.controller.SectionController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SectionController.class)
class SectionControllerTest {

  @Autowired private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @MockitoBean private SectionService sectionService;

  @Test
  @DisplayName("Test - Should return a Game object when the parameters are correct.")
  void testGetBooks_Found() throws Exception {
    long gameId = 9;
    long sectionId= 10;
    SectionDto sectionDto = new SectionDto();

    when(sectionService.getBookBySectionIdAndBookId(gameId , sectionId)).thenReturn(sectionDto);

    mockMvc.perform(get("/section/{id}/section/{sectionId}", gameId , sectionId)).andExpect(status().isOk());
  }

  @Test
  @DisplayName("Test - Should return a not found when the parameters are incorrect.")
  void testGetBooks_not_Found() throws Exception {
    long gameId = 9;
    long sectionId= 10;

    when(sectionService.getBookBySectionIdAndBookId(gameId , sectionId)).thenThrow(BookError.class);

    mockMvc.perform(get("/section/{id}/section/{sectionId}", gameId , sectionId)).andExpect(status().isBadRequest());
  }

}
