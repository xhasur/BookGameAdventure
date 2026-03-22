package com.pictet.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

  private Long id;
  private Integer idSection;
  private Integer health;
  private String gameStatus;
}
