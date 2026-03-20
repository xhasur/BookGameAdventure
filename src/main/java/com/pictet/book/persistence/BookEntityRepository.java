package com.pictet.book.persistence;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.repository.BookRepository;
import com.pictet.book.persistence.crud.CrudBookEntity;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.mapper.BookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookEntityRepository implements BookRepository {

    private final CrudBookEntity crudBookEntity;
    private final BookMapper bookMapper;

    public BookEntityRepository(CrudBookEntity crudBookEntity, BookMapper bookMapper) {
        this.crudBookEntity = crudBookEntity;
        this.bookMapper = bookMapper;
    }


    @Override
    public List<BookDto> getAll() {
        return this.bookMapper.toDto(this.crudBookEntity.findAll());
    }

    @Override
    public BookDto save(BookDto book) {
        Book bookEntity = this.bookMapper.toEntity(book);
        return this.bookMapper.toDto(this.crudBookEntity.save(bookEntity));
    }
}
