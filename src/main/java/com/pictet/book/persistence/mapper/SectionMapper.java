package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.persistence.entity.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {OptionMapper.class})
public interface SectionMapper {

    @Mapping(source = "idSection", target = "id" )
    SectionDto toDto(Section entity);

    List<SectionDto> toDto(Iterable<Section> entities);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "idSection" )
    Section toEntity(SectionDto dto);

}
