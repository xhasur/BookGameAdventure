package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.GameDto;
import com.pictet.book.persistence.entity.Game;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

  GameDto toDto(Game entity);

  List<GameDto> toDto(Iterable<Game> entities);

  @InheritInverseConfiguration
  Game toEntity(GameDto dto);
}
