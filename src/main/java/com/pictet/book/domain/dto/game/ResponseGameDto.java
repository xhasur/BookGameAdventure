package com.pictet.book.domain.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGameDto {

    private Long gameId;
    private String playerName;
    private Integer health;
    private boolean finished;
    private boolean dead;
    private SectionGameDto currentSection;
}
