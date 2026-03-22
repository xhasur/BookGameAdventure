package com.pictet.book.domain.dto.game;

import com.pictet.book.domain.dto.SectionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

  private Long id;
  private Integer idSection;
  private Integer health;
  private String gameStatus;
  private List<SectionDto> sections;
}
