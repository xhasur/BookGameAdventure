package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.OptionDto;
import com.pictet.book.persistence.entity.Option;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ConsequenceMapper.class})
public interface OptionMapper {

    OptionDto toDto(Option entity);

    List<OptionDto> toDto(Iterable<Option> entities);

    @InheritInverseConfiguration
    Option toEntity(OptionDto dto);
}
