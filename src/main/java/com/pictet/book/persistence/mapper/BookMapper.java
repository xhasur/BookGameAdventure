package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.persistence.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring" , uses = {DifficultMapper.class})
public interface BookMapper {

    @Mapping(source = "difficulty", target = "difficulty" , qualifiedByName = "stringToDifficult")
    BookDto toDto(Book entity);
    List<BookDto> toDto(Iterable<Book> entities);

}
