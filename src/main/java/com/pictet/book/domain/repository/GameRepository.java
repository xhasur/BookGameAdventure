package com.pictet.book.domain.repository;

import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.persistence.entity.Game;

public interface GameRepository {

  void saveGame(Game game);

  GameDto findById(long id);

  Game getGame(long id);
}
