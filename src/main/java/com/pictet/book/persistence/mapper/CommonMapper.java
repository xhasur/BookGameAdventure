package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.game.ResponseGameDto;
import com.pictet.book.domain.dto.game.OptionGameDto;
import com.pictet.book.domain.dto.game.SectionGameDto;
import com.pictet.book.persistence.entity.Game;
import com.pictet.book.persistence.entity.Section;
import java.util.List;
import java.util.stream.Collectors;

import static com.pictet.book.web.util.Constants.GAME_STATUS_DEAD;
import static com.pictet.book.web.util.Constants.GAME_STATUS_FINISHED;

public class CommonMapper {

  public static ResponseGameDto mapToResponse(Game game) {
    ResponseGameDto response = new ResponseGameDto();

    response.setGameId(game.getId());
    response.setHealth(game.getHealth());
    response.setPlayerName(game.getPlayerName());

    response.setFinished(GAME_STATUS_FINISHED.equals(game.getGameStatus()));
    response.setDead(GAME_STATUS_DEAD.equals(game.getGameStatus()));

    Section section = game.getBook().getSections().stream()
            .filter(s -> s.getIdSection().equals(game.getIdSection()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Section not found"));

    SectionGameDto sectionDto = new SectionGameDto();
    sectionDto.setId(section.getId());
    sectionDto.setText(section.getText());

    List<OptionGameDto> options = section.getOptions().stream()
            .map(option -> new OptionGameDto(option.getDescription(), option.getGotoId()))
            .collect(Collectors.toList());

    sectionDto.setOptions(options);
    response.setCurrentSection(sectionDto);
    return response;
  }
}
