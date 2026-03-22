package com.pictet.book.persistence;

import com.pictet.book.domain.dto.GameDto;
import com.pictet.book.domain.repository.GameRepository;
import com.pictet.book.persistence.crud.CrudGameEntity;
import com.pictet.book.persistence.entity.Game;
import com.pictet.book.persistence.mapper.GameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GameEntityRepository implements GameRepository {
  private final CrudGameEntity crudGameEntity;
  private final GameMapper gameMapper;

  public GameEntityRepository(CrudGameEntity crudGameEntity, GameMapper gameMapper) {

    this.crudGameEntity = crudGameEntity;
    this.gameMapper = gameMapper;
  }

  @Override
  public void saveGame(Game game) {
      this.crudGameEntity.save(game);
  }

  @Override
  public GameDto findById(long id) {
      return this.gameMapper.toDto(this.crudGameEntity.findById(id).orElse(null));
    }

}
