package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.ConsequenceDto;
import com.pictet.book.persistence.entity.Consequence;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsequenceMapper {

  ConsequenceDto toDto(Consequence entity);

  List<ConsequenceDto> toDto(Iterable<Consequence> entities);

  @InheritInverseConfiguration
  Consequence toEntity(ConsequenceDto dto);
}
