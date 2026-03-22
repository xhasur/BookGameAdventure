package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.game.Game;
import com.pictet.book.domain.dto.game.OptionGameDto;
import com.pictet.book.domain.dto.game.SectionGameDto;
import com.pictet.book.persistence.entity.Section;
import java.util.List;
import java.util.stream.Collectors;

import static com.pictet.book.web.util.Constants.GAME_STATUS_DEAD;
import static com.pictet.book.web.util.Constants.GAME_STATUS_FINISHED;

public class CommonMapper {

  public static Game mapToResponse(
      com.pictet.book.persistence.entity.Game session, Section section) {
    Game response = new Game();

    response.setSessionId(session.getId());
    response.setHealth(session.getHealth());

    response.setFinished(GAME_STATUS_FINISHED.equals(session.getGameStatus()));
    response.setDead(GAME_STATUS_DEAD.equals(session.getGameStatus()));

    SectionGameDto sectionDto = new SectionGameDto();
    sectionDto.setId(section.getId());
    sectionDto.setText(section.getText());

    List<OptionGameDto> options = section.getOptions()
            .stream()
            .map(option -> new OptionGameDto(option.getDescription(), option.getGotoId()))
            .collect(Collectors.toList());

    sectionDto.setOptions(options);
    response.setCurrentSection(sectionDto);
    return response;
  }
}
