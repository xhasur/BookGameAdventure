package com.pictet.book.domain.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Long sessionId;
    private Integer health;
    private boolean finished;
    private boolean dead;
    private SectionGameDto currentSection;
}
