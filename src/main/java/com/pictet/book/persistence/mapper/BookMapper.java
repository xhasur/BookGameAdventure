package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.persistence.entity.Book;
import java.util.List;
//, uses = {DifficultMapper.class}
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {SectionMapper.class } )
public interface BookMapper {

    //@Mapping(source = "difficulty", target = "difficulty" , qualifiedByName = "stringToDifficult")
    BookDto toDto(Book entity);

    List<BookDto> toDto(Iterable<Book> entities);

    @InheritInverseConfiguration
    //@Mapping(source = "difficulty", target = "difficulty" , qualifiedByName = "difficultToString")
    Book toEntity(BookDto dto);

}
