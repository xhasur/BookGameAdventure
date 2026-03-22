package com.pictet.book.domain.dto.game;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionGameDto {

  private Long id;
  private String text;
  private List<OptionGameDto> options;
}
