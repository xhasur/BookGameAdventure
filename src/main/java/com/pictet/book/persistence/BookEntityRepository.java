package com.pictet.book.persistence;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.exception.BookAlreadyExistsException;
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
    if (this.crudBookEntity.findFirstByTitle(book.getTitle()) != null) {
      throw new BookAlreadyExistsException(book.getTitle());
    }
        Book bookEntity = this.bookMapper.toEntity(book);
        if (bookEntity.getSections() != null) {
            bookEntity.getSections().forEach(section -> {
                section.setBook(bookEntity);
                if (section.getOptions() != null) {
                    section.getOptions().forEach(option -> {
                        option.setSection(section);

                    });
                }
            });
        }
        return this.bookMapper.toDto(this.crudBookEntity.save(bookEntity));
    }

    @Override
    public List<BookDto> getBooksByConditions(String title, String author, String difficulty, String category) {
        return this.bookMapper.toDto(this.crudBookEntity.searchBooks(title, author, difficulty, category));
    }


    @Override
    public BookDto findById(long id) {
        return this.bookMapper.toDto(this.crudBookEntity.findById(id).orElse(null));
    }

    public Book getBook(long id) {
        return this.crudBookEntity.findById(id).orElse(null);
    }

    public BookDto saveBook(Book id) {
        return this.bookMapper.toDto(this.crudBookEntity.save(id));
    }
}
